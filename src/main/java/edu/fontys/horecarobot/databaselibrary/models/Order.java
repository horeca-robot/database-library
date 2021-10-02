package edu.fontys.horecarobot.databaselibrary.models;

import edu.fontys.horecarobot.databaselibrary.enums.PaymentStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column
    private float subTotal;

    @Column
    private PaymentStatus paymentStatus;

    @Column
    private Date created_at;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable table;


    public Order(){

    }

    public Order(float subTotal, PaymentStatus paymentStatus) {
        this.subTotal = subTotal;
        this.paymentStatus = paymentStatus;
        this.created_at = new Date();
    }
}
