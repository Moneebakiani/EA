package edu.miu.cs.cs544.examples;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  int studentid;
    private  String firstname;
    private  String lastname;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="student_course")
    private List<Course> courses=new ArrayList<>();

    public Student(int studentid, String firstname, String lastname) {
        this.studentid = studentid;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public  void addCourse(Course course){
        courses.add(course);
        course.addStudent(this);
    }
}
