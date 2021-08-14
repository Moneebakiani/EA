package cs544.exercise15_2.bank.service;

import java.util.Collection;

import cs544.exercise15_2.bank.domain.AccountEntry;
import cs544.exercise15_2.bank.logging.ILogger;
import cs544.exercise15_2.bank.dao.AccountDAO;
import cs544.exercise15_2.bank.dao.IAccountDAO;
import cs544.exercise15_2.bank.domain.Account;
import cs544.exercise15_2.bank.domain.Customer;
import cs544.exercise15_2.bank.jms.IJMSSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements IAccountService {
    private IAccountDAO accountDAO;
    private ICurrencyConverter currencyConverter;
    private IJMSSender jmsSender;
    private ILogger logger;

    @Autowired
    public AccountService(IAccountDAO accountDAO,
                          ICurrencyConverter currencyConverter,
                          IJMSSender jmsSender, ILogger logger) {
        this.accountDAO = accountDAO;
        this.currencyConverter = currencyConverter;
        this.jmsSender = jmsSender;
        this.logger = logger;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account createAccount(long accountNumber, String customerName) {
        Account account = new Account(accountNumber);
        Customer customer = new Customer(customerName);
        account.setCustomer(customer);
        accountDAO.saveAccount(account);
        logger.log("createAccount with parameters accountNumber= " + accountNumber + " , customerName= " + customerName);
        return account;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deposit(long accountNumber, double amount) {

        Account account = accountDAO.loadAccount(accountNumber);
        account.deposit(amount);
        accountDAO.updateAccount(account);


        logger.log("deposit with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account getAccount(long accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        return account;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Collection<Account> getAllAccounts() {
        Collection<Account> accounts = accountDAO.getAccounts();
        return accounts;
    }

    public void withdraw(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.withdraw(amount);
        accountDAO.updateAccount(account);
        logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void depositEuros(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.deposit(amountDollars);
        accountDAO.updateAccount(account);
        logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        if (amountDollars > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void withdrawEuros(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.withdraw(amountDollars);
        accountDAO.updateAccount(account);
        logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
        Account toAccount = accountDAO.loadAccount(toAccountNumber);
        fromAccount.transferFunds(toAccount, amount, description);
        accountDAO.updateAccount(fromAccount);
        accountDAO.updateAccount(toAccount);
        logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber + " , toAccountNumber= " + toAccountNumber + " , amount= " + amount + " , description= " + description);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("TransferFunds of $ " + amount + " from account with accountNumber= " + fromAccount + " to account with accountNumber= " + toAccount);
        }
    }

    @Scheduled(cron = "0/10 * * * * *")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void printAccountStatements() {
        // show balances
        Collection<Account> accountList = getAllAccounts();
        Customer customer;
        for (Account account : accountList) {
            customer = account.getCustomer();
            System.out.println("Statement for Account: " + account.getAccountnumber());
            System.out.println("Account Holder: " + customer.getName());
            System.out.println("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");
            for (AccountEntry entry : account.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n", entry.getDate()
                        .toString(), entry.getDescription(), entry.getAmount());
            }
            System.out.println("----------------------------------------"
                    + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
                    account.getBalance());
        }

    }

    public void setAccountDao(AccountDAO accountDao) {
        this.accountDAO = accountDao;
    }

    public void setCurrencyConverter(ICurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    public void setJmsSender(IJMSSender jmsSender) {
        this.jmsSender = jmsSender;
    }

    public void setLogger(ILogger logger) {
        this.logger = logger;
    }
}
