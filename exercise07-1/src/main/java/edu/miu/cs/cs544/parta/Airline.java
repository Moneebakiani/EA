package edu.miu.cs.cs544.parta;

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
@ToString(exclude = {"flights"})
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "airline")
    List<Flight> flights=new ArrayList<>();

    public Airline(String name) {
        this.name = name;
    }
    public void addFlight(Flight flight){
        this.flights.add(flight);
    }
}
