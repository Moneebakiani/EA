package edu.miu.cs.cs544.partb;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Application {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Customer.class, Order.class, OrderLine.class, Product.class,CD.class,DVD.class,Book.class));
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Customer customer = new Customer("Moneeba", "Kiani");
            Order order = new Order("o1", LocalDate.now());

            OrderLine orderline1 = new OrderLine(2);
            OrderLine orderline2 = new OrderLine(3);
            OrderLine orderline3 = new OrderLine(4);
            OrderLine orderline4 = new OrderLine(1);

            Product product = new Product("Tshirt", "Beautiful design");
            Product CD= new CD("this is cd","Moni");
            Product DVD= new DVD("this is DVD","Science Fiction");
            Product Book= new Book("book","this is Book");

            orderline1.setProduct(product);
            orderline2.setProduct(Book);
            orderline3.setProduct(CD);
            orderline4.setProduct(DVD);

            order.addOrderLine(orderline1);
            order.addOrderLine(orderline2);
            order.addOrderLine(orderline3);
            order.addOrderLine(orderline4);

            customer.addOrder(order);
            session.persist(customer);
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
            List<Customer> customerList = session.createQuery("from Customer", Customer.class).list();
            for (Customer customer : customerList) {
                System.out.println(customer);
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