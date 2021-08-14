package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sun.security.krb5.internal.APOptions;

public class Application {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Appointment.class,Doctor.class,Patient.class,Payment.class));
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Doctor doctor=new Doctor("Surgen","Yayha","Dialo");
            Patient patient=new Patient("Alvin","4th","52557","Fairfield");
            Payment payment= new Payment("7/25,2021",5000.0);
            Appointment appointment=new Appointment("7/26/2021",patient,payment,doctor);

            // save the car
            session.persist(appointment);

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
            List<Appointment> appointemntList = session.createQuery("from Appointment", Appointment.class).list();
            for (Appointment appointemnt : appointemntList) {
                System.out.println(appointemnt);
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