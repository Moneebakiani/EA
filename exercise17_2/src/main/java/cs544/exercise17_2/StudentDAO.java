package cs544.exercise17_2;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class StudentDAO {

	private final SessionFactory sf;

	@Autowired
	public StudentDAO(SessionFactory sf) {
		this.sf = sf;
//		Student student = new Student(11334, "Frank", "Brown");
//		Course course1 = new Course(1101, "Java", "A");
//		Course course2 = new Course(1102, "Math", "B-");
//		student.addCourse(course1);
//		student.addCourse(course2);
//		sf.getCurrentSession().persist(student);
	}

	public Student load(long studentid) {
		Query<Student> query = sf.getCurrentSession()
				.createQuery("from Student s where s.studentid = :stdid", Student.class)
				.setParameter("stdid", studentid);
		return query.uniqueResult();
	}
}
