package cs544.exercise16_1.bank.dao;

import java.util.*;

import cs544.exercise16_1.bank.domain.Account;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public class AccountDAO implements IAccountDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveAccount(Account account) {
        sessionFactory.getCurrentSession().persist(account);
    }

    public void updateAccount(Account account) {
        sessionFactory.getCurrentSession().saveOrUpdate(account);
    }

    public Account loadAccount(long accountnumber) {
        Query<Account> query = sessionFactory.getCurrentSession()
                .createQuery("from Account a where a.accountnumber = :accountnumber", Account.class);
        query.setParameter("accountnumber", accountnumber);
        return query.uniqueResult();
    }

    public Collection<Account> getAccounts() {
        return sessionFactory.getCurrentSession().createQuery("from Account", Account.class).list();
    }

}
