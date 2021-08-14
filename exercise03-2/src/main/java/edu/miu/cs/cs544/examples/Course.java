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
@ToString(exclude = {"students"})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String coursenumber;
    private String name;
    @ManyToMany(mappedBy = "courses")
    private List<Student> students=new ArrayList<>();

    public Course(String coursenumber, String name) {
        this.coursenumber = coursenumber;
        this.name = name;
    }
    public  void addStudent(Student student){
        students.add(student);
    }
}
