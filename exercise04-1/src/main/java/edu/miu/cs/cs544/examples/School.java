package edu.miu.cs.cs544.examples;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "school_id")
    @MapKey(name = "studentid")
    private Map<String, Student> students = new HashMap<>();

    public School(String name) {
        this.name = name;
    }

    public void addStudent(Student student){
        this.students.put(student.getStudentid(),student);
    }
}
