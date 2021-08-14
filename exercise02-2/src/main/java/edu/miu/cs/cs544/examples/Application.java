package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Laptop.class));
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Create new instance of Employee and set values in it
            Laptop laptop1 = new Laptop("Dell", "XPS 15", LocalDate.now());
            Laptop laptop2 = new Laptop("Samsung", "Galaxy Book Pro 360", LocalDate.now());

            // save the book
            session.persist(laptop1);
            session.persist(laptop2);


            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            // retrieve all books
            List<Laptop> laptopList = session.createQuery("from Laptop", Laptop.class).list();
            for (Laptop laptop : laptopList) {
                System.out.println(laptop);
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }


        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
    }
}