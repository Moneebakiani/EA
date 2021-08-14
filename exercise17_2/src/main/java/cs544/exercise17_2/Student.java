package cs544.exercise17_2;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data

public class Student {
	@Id
	private long studentid;
	private String firstname;
	private String lastname;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Collection<Course> courselist = new ArrayList<Course>();


	public Student(long studentid, String firstname, String lastname) {
		this.studentid = studentid;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public void addCourse(Course course) {
		this.courselist.add(course);
	}

}
