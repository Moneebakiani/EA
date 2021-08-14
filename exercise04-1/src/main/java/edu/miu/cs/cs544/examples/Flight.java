package edu.miu.cs.cs544.examples;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String flightno;
    private String origin;
    private String destination;
    private LocalDate date;

    public Flight(String flightno, String origin, String destination, LocalDate date) {
        this.flightno = flightno;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }
}
