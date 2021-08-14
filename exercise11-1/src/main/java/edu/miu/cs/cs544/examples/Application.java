package edu.miu.cs.cs544.examples;

import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Application {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Greeting.class));
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new
                ClassPathXmlApplicationContext("springconfig.xml");
        Greeting greetingService =
                context.getBean("greetingService", Greeting.class);
        greetingService.sayHello();
    }
}