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
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Employee.class,Laptop.class,Passanger.class,Flight.class,School.class,Student.class));
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

//           Employee emp1=new Employee("John","smith");
//           Laptop laptop1=new Laptop("HP","A3456");
//           Laptop laptop2=new Laptop("Dell","d786");
//           Laptop laptop3=new Laptop("Apple","g7873");
//
//           emp1.addLaptop(laptop1);
//           emp1.addLaptop(laptop2);
//           emp1.addLaptop(laptop3);


//            Passanger passanger=new Passanger("Moneeba Kiani");
//            Flight flight1=new Flight("1","Newyork","Iowa",LocalDate.now());
//            Flight flight2=new Flight("2","Carolina","New York",LocalDate.now());
//            passanger.addFlight(flight1);
//            passanger.addFlight(flight2);


            School school=new School("MIU");
            Student std1=new Student("612436","Moneeba","Kiani");
            Student std2=new Student("612418","Alvin","Abaho");
            Student std3=new Student("612410","Yahya","Dialo");
            school.addStudent(std1);
            school.addStudent(std2);
            school.addStudent(std3);

            // save the car
            session.persist(school);

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
            List<School> schoolList = session.createQuery("from School", School.class).list();
            for (School school : schoolList) {
                System.out.println(school);
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