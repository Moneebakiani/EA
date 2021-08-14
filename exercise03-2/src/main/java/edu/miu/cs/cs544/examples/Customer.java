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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="customer_reservation")
    private List<Reservation> reservationList=new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addReservation(Reservation reservation){
        reservationList.add(reservation);
    }
}
