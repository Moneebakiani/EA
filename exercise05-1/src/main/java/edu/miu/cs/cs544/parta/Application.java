package edu.miu.cs.cs544.parta;

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
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Customer.class, Order.class, OrderLine.class, Product.class));
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
            OrderLine orderLine = new OrderLine(2);
            Product product = new Product("Tshirt", "Beautiful design");
            order.addOrderLine(orderLine);
            orderLine.setProduct(product);
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