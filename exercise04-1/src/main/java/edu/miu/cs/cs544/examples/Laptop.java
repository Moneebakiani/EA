package edu.miu.cs.cs544.examples;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = {"employee"})
@EqualsAndHashCode
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String type;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="employee_id")
    private Employee employee;

    public Laptop(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }
}
