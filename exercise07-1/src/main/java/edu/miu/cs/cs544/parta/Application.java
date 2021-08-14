package edu.miu.cs.cs544.parta;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Application {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Airline.class, Airplane.class, Airport.class, Flight.class));
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        // fill the database
        fillDataBase();

        // a) Flights leaving USA capacity > 500
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // TODO update HQL
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Flight f where f.origin.country= :country and " +
                    "f.airplane.capacity > :capacity");
            query.setParameter("country", "USA").setParameter("capacity", 500);
            List<Flight> flights = query.list();


            System.out.println("Flight:  Departs:     "
                    + "                  Arrives:");
            for (Flight flight : flights) {
                System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                        flight.getFlightnr(), flight.getOrigin().getCity(),
                        flight.getDepartureDate(), flight.getDepartureTime(),
                        flight.getDestination().getCity(), flight
                                .getArrivalDate(), flight.getArrivalTime());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace(System.err);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }


        // b) All airlines that use A380 airplanes
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // TODO update HQL
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("select distinct a from Airline a join a.flights f where f.airplane.model= :model");
            query.setParameter("model", "A380");
            List<Airline> airlines = query.list();
            System.out.println("Airlines:");
            for (Airline airline : airlines) {
                System.out.printf("%-15s\n", airline.getName());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace(System.err);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }


        // c) Flights using 747 planes that don't belong to Star Alliance
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // TODO update HQL
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Flight f where f.airplane.model= :model and f.airline.name != :airline");
            query.setParameter("model", "747").setParameter("airline", "Star Alliance");
            List<Flight> flights = query.list();
            System.out.println("Flight:  Departs:     "
                    + "                  Arrives:");
            for (Flight flight : flights) {
                System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                        flight.getFlightnr(), flight.getOrigin().getCity(),
                        flight.getDepartureDate(), flight.getDepartureTime(),
                        flight.getDestination().getCity(), flight
                                .getArrivalDate(), flight.getArrivalTime());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace(System.err);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }


        // d) All flights leaving before 12pm on 08/07/2009
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // TODO update HQL
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Flight f where f.departureDate = :date and f.departureTime < :time");
            query.setParameter("date", LocalDate.of(2009,8,7)).setParameter("time", LocalDateTime.of(2009,8,7, 12, 0));
            List<Flight> flights = query.list();
            System.out.println("Flight:  Departs:     "
                    + "                  Arrives:");
            for (Flight flight : flights) {
                System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                        flight.getFlightnr(), flight.getOrigin().getCity(),
                        flight.getDepartureDate(), flight.getDepartureTime(),
                        flight.getDestination().getCity(), flight
                                .getArrivalDate(), flight.getArrivalTime());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace(System.err);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
    }

    public static void fillDataBase() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Airline airline1 = new Airline("American");
            Airline airline2 = new Airline("Southwest");
            Airline airline3 = new Airline("Star Alliance");

            Airplane airplane1 = new Airplane(737800, "Boeing", 600);
            Airplane airplane2 = new Airplane(737700, "A380", 400);
            Airplane airplane3 = new Airplane(737600, "747", 400);

            Airport airport1 = new Airport("Cedar Ridge", "Fairfield", "USA", "a546");
            Airport airport2 = new Airport("JFK", "Jamaica", "USA", "a746");

            Flight flight1 = new Flight("a7200", LocalDate.now(), LocalDateTime.now(),
                    LocalDate.now(), LocalDateTime.now(), airline1, airplane1, airport1, airport2);
            Flight flight2 = new Flight("a6600", LocalDate.now(), LocalDateTime.now(),
                    LocalDate.now(), LocalDateTime.now(), airline2, airplane2, airport2, airport1);
            Flight flight3 = new Flight("a5500", LocalDate.now(), LocalDateTime.now(),
                    LocalDate.now(), LocalDateTime.now(), airline2, airplane3, airport2, airport1);
            Flight flight4 = new Flight("a2200", LocalDate.of(2009, 8, 7), LocalDateTime.of(2009, 8, 7, 11, 15),
                    LocalDate.of(2009, 8, 7), LocalDateTime.of(2009, 8, 7, 3, 15), airline3, airplane3, airport2, airport1);


            session.persist(flight1);
            session.persist(flight2);
            session.persist(flight3);
            session.persist(flight4);
            tx.commit();

        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    }

}