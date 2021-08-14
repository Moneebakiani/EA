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
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Order> orderList=new ArrayList<>();

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void addOrder(Order order){
        orderList.add(order);
        order.setCustomer(this);
    }
}
