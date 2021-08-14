package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ApplicationPerson {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Person.class));
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Create new instance of Employee and set values in it
            Person person1 = new Person("Moneeba", "Kiani", LocalDate.of(1995,1,18));
            Person person2 = new Person("Angelique", "shaukat", LocalDate.of(1994,7,28));
            Person person3 = new Person("Javeria", "Tauseef", LocalDate.of(1990,9,26));

            // save the book
            session.persist(person1);
            session.persist(person2);
            session.persist(person3);


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
            List<Person> personList = session.createQuery("from Person", Person.class).list();
            for (Person person : personList) {
                System.out.println(person);
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }


        // update

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            // updating all books
            Person personupdate=(Person) session.get(Person.class, 2);
            personupdate.setFirstName("Hira");
            personupdate.setLastName("Khan");
            session.update(personupdate);
            // deleting book
            Person deleteperson=(Person) session.load(Person.class,3);
            session.delete(deleteperson);

            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

        // output of all books on console
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            // retrieve all books
            List<Person> personList = session.createQuery("from Person", Person.class).list();
            for (Person person : personList) {
                System.out.println(person);
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