package cs544.exercise16_2;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data

public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private long coursenumber;
	private String name;
	private String grade;

	public Course(long coursenumber, String name, String grade) {
		this.coursenumber = coursenumber;
		this.name = name;
		this.grade = grade;
	}


	public long getCoursenumber() {
		return coursenumber;
	}

	public void setCoursenumber(long coursenumber) {
		this.coursenumber = coursenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
