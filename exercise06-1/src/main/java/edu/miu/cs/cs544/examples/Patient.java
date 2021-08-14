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
@SecondaryTables(
        @SecondaryTable(name="Address", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "PATIENT_ID", referencedColumnName = "id")
        })
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private  int id;
    @Column(name = "NAME")
    private String name;

    @Column(table = "Address", name = "STREET")
    private String street;
    @Column(table = "Address", name = "ZIP")
    private String zip;
    @Column(table = "Address", name = "CITY")
    private String city;

    public Patient(String name, String street, String zip, String city) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.city = city;
    }
}
