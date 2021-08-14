package cs544.exercise16_2;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;

public class StudentDAO {

	private SessionFactory sf = HibernateUtil.getSessionFactory();

	public StudentDAO() {
		Student student = new Student(11334, "Frank", "Brown");
		Course course1 = new Course(1101, "Java", "A");
		Course course2 = new Course(1102, "Math", "B-");
		student.addCourse(course1);
		student.addCourse(course2);
		sf.getCurrentSession().persist(student);

	}

	public Student load(long studentid) {
		Query<Student> query = sf.getCurrentSession()
				.createQuery("from Student s where s.studentid = :stdid", Student.class)
				.setParameter("stdid", studentid);
		return query.uniqueResult();
	}
}
