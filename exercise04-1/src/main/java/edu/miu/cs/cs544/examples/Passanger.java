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
public class Passanger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passenger_id")
    @OrderColumn(name = "sequence")
    List<Flight> flights = new ArrayList<>();

    public Passanger(String name) {
        this.name = name;
    }

    public void addFlight(Flight flight){
        this.flights.add(flight);
    }
}
