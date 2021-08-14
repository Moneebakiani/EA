package edu.miu.cs.cs544.parta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String flightnr;
    private LocalDate departureDate;
    private LocalDateTime departureTime;
    private LocalDate arrivalDate;
    private LocalDateTime arrivalTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="origin_id")
    private Airport origin;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="destination_id")
    private Airport destination;

    public Flight(String flightnr, LocalDate departureDate, LocalDateTime departureTime, LocalDate arrivalDate, LocalDateTime arrivalTime) {
        this.flightnr = flightnr;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
    }

    public Flight(String flightnr, LocalDate departureDate, LocalDateTime departureTime, LocalDate arrivalDate,
                  LocalDateTime arrivalTime, Airline airline, Airplane airplane, Airport origin, Airport destination) {
        this.flightnr = flightnr;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        setAirplane(airplane);
        setAirline(airline);
        setOrigin(origin);
        setDestination(destination);
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
        airline.addFlight(this);
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
        airplane.addFlight(this);
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
        origin.addDeparture(this);
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
        destination.addArrival(this);
    }
}
