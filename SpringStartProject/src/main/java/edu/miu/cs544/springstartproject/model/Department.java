package edu.miu.cs544.springstartproject.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "DepartmentID")
    private Integer id;

    private String Name;

    private String GroupName;

    private LocalDate ModifiedDate;
}
