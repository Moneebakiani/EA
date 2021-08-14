package edu.miu.cs.cs544.examples;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
@ToString
@NoArgsConstructor
@Embeddable
public class Payment {
    private  String paydate;
    private double amount;

    public Payment(String paydate, double amount) {
        this.paydate = paydate;
        this.amount = amount;
    }
}
