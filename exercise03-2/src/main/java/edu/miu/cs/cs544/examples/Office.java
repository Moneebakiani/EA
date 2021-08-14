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
@ToString(exclude = {"employeeList"})
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int roomnumber;
    private String building;

    @OneToMany(mappedBy = "office", cascade = CascadeType.PERSIST)
    private List<Employee> employeeList=new ArrayList<>();

    public Office(int roomnumber, String building) {
        this.roomnumber = roomnumber;
        this.building = building;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
}
