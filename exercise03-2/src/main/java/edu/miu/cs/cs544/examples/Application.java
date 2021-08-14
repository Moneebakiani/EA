package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Department.class, Employee.class, Book.class,Publisher.class, Student.class, Course.class, Customer.class, Reservation.class,Office.class));
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

//            Department dep=new Department("compro");
//            Employee employee1 = new Employee(123,"Alvin");
//            Employee employee2 = new Employee(456,"Addisu");
//            dep.addEmployee(employee1);
//            dep.addEmployee(employee2);
//            session.persist(dep);

//            Book book1 = new Book("a123", "title1", "author1");
//            Book book2 = new Book("a456", "title2", "author2");
//            Publisher publisher= new Publisher("Yaya");
//            Publisher publisher1= new Publisher("alvin");
//            book1.setPublisher(publisher);
//            book2.setPublisher(publisher1);
//            session.persist(book1);
//            session.persist(book2);


//            Student student1=new Student(612436,"Moneeba", "Kiani");
//            Student student2=new Student(612439,"KM", "Hira");
//            Course course1=new Course("CS544","EA");
//            Course course2=new Course("CS572","MWA");
//
//            student1.addCourse(course1);
//            student1.addCourse(course2);
//            student2.addCourse(course2);
//            student2.addCourse(course1);
//
//            session.persist(student1);
//            session.persist(student2);


//            Customer customer=new Customer("Moneeba");
//            customer.addReservation(new Reservation(LocalDate.now(),new Book("a123", "title1", "author1")));
//            session.persist(customer);

            Department dep=new Department("compro");
            Office office=new Office(32, "verril");
            Employee employee1 = new Employee(123,"Alvin");
            Employee employee2 = new Employee(456,"Addisu");
            employee1.addOffice(office);
            employee2.addOffice(office);
            dep.addEmployee(employee1);
            dep.addEmployee(employee2);
            session.persist(dep);

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
            List<Employee> employeeList = session.createQuery("from Employee", Employee.class).list();
            for (Employee employee : employeeList) {
                System.out.println(employee);
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