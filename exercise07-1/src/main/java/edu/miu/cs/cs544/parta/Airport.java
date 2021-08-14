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
@ToString(exclude = {"arrivals", "departures"})
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String city;
    private String country;
    private String airportcode;

    @OneToMany(mappedBy = "destination",cascade = CascadeType.PERSIST)
    private List<Flight> arrivals =new ArrayList<>();

    @OneToMany(mappedBy = "origin",cascade = CascadeType.PERSIST)
    private List<Flight> departures =new ArrayList<>();

    public Airport(String name, String city, String country, String airportcode) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.airportcode = airportcode;
    }

    public void addDeparture(Flight flight) {
        departures.add(flight);
    }

    public void addArrival(Flight flight) {
        arrivals.add(flight);
    }
}

