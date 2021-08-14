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
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Car.class,Owner.class));
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Create new instance of car

            Car car1 = new Car("audi", 2020, 7000);
            Car car2 = new Car("prado", 2010, 10000);
            car1.setOwner(new Owner("John","Iowa"));
            car2.setOwner(new Owner("Smith","Newyork"));

            // save the car
            session.persist(car1);
            session.persist(car2);
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
            List<Car> carList = session.createQuery("from Car", Car.class).list();
            for (Car car : carList) {
                System.out.println(car);
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