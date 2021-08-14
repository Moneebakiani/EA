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
@ToString(exclude = {"flightList"})
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int serialnr;
    private String model;
    private int capacity;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.PERSIST)

    private List<Flight> flightList = new ArrayList<>();

    public Airplane(int serialnr, String model, int capacity) {
        this.serialnr = serialnr;
        this.model = model;
        this.capacity = capacity;
    }

    public void addFlight(Flight flight) {
        flightList.add(flight);
    }
}
