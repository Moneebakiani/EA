package edu.miu.cs.cs544.parta;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

public class HibernateUtils {
    private static SessionFactory sessionFactory;
    
    private static Configuration configuration = new Configuration();
    
    @SuppressWarnings({ "rawtypes" })
    public static SessionFactory getSessionFactory(List<Class> entityClasses) {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/cs544?serverTimezone=UTC&useSSL=false");
                settings.put(Environment.USER, "devuser");
                settings.put(Environment.PASS, "test");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "false");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                
                entityClasses.forEach(entityClass -> configuration.addAnnotatedClass(entityClass));

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return sessionFactory;
    }
    
}
