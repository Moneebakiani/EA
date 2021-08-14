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
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "APPDATE")
    private String appdate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="PATIENT")
    private Patient patient;

    @AttributeOverrides({
            @AttributeOverride(name = "paydate",
                    column = @Column(name = "PAYDATE")),
            @AttributeOverride(name = "amount",
                    column = @Column(name = "AMOUNT"))
    })

    @Embedded
    private Payment payment;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="DOCTOR")
    private Doctor doctor;

    public Appointment(String appdate, Patient patient, Payment payment, Doctor doctor) {
        this.appdate = appdate;
        this.patient = patient;
        this.payment = payment;
        this.doctor = doctor;
    }
}
