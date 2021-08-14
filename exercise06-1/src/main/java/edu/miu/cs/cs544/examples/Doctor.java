package edu.miu.cs.cs544.examples;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private  int id;
    @Column(name = "TYPE")
    private String doctortypr;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;

    public Doctor(String doctortypr, String firstname, String lastname) {
        this.doctortypr = doctortypr;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
