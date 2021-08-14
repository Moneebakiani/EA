package cs544.exercise15_2.bank.dao;

import cs544.exercise15_2.bank.domain.Account;
import cs544.exercise15_2.bank.domain.AccountEntry;
import cs544.exercise15_2.bank.domain.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	private static final List<Class<?>> entityClasses = Arrays.asList(
			Account.class, AccountEntry.class, Customer.class);
	static {
		try {
			Configuration configuration = new Configuration();
			Properties settings = new Properties();
			settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			settings.put(Environment.URL, "jdbc:mysql://localhost:3306/cs544?serverTimezone=UTC&useSSL=false");
			settings.put(Environment.USER, "devuser");
			settings.put(Environment.PASS, "test");
			settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
			// properties.put(Environment.SHOW_SQL, "true");
			// properties.put(Environment.USE_SQL_COMMENTS, "true");
			// properties.put(Environment.FORMAT_SQL, "true");
			// use thread local pattern
			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			// Always drop and recreate the database schema on startup
			settings.put(Environment.HBM2DDL_AUTO, "create-drop");
			configuration.setProperties(settings);
			entityClasses.forEach(configuration::addAnnotatedClass);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}