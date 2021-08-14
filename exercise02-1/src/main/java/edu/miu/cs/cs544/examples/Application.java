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
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Book.class));
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Create new instance of Employee and set values in it
            Book book1 = new Book("THE PAPER PALACE", "book01", "Miranda Cowley Heller", 29.9, LocalDate.now());
            Book book2 = new Book("NINE LIVES", "book02", "Danielle Steel", 25.9, LocalDate.now());
            Book book3 = new Book("FALLING", "book03", "T.J. Newman", 20, LocalDate.now());

            // save the book
            session.persist(book1);
            session.persist(book2);
            session.persist(book3);

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
            List<Book> bookList = session.createQuery("from Book", Book.class).list();
            for (Book book : bookList) {
                System.out.println(book);
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
            Book bookupdate=(Book) session.get(Book.class, 2);
            bookupdate.setTitle("THIS IS YOUR MIND ON PLANTS");
            bookupdate.setPrice(30);
            session.update(bookupdate);
            // deleting book
            Book deletebook=(Book) session.load(Book.class,3);
            session.delete(deletebook);

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
            List<Book> bookList = session.createQuery("from Book", Book.class).list();
            for (Book book : bookList) {
                System.out.println(book);
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