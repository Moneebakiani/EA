package edu.miu.cs.cs544.examples;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int employeenumber;
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="department_id")
    private Department department;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="office_id")
    private Office office;

    public Employee(int employeenumber, String name) {
        this.employeenumber = employeenumber;
        this.name = name;
    }

    public void addOffice(Office office){
        this.office=office;
        office.addEmployee(this);

    }
}
