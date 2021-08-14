package edu.miu.cs.cs544.examples;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;
    private LocalDate purchase_date;

    public Laptop() {
    }

    public Laptop(String brand, String model, LocalDate purchase_date) {
        this.brand = brand;
        this.model = model;
        this.purchase_date = purchase_date;
    }
}
