package edu.fontys.horecarobot.databaselibrary.models;

import edu.fontys.horecarobot.databaselibrary.enums.PaymentStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
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
    private double subtotal;

    @Column
    private PaymentStatus paymentStatus;

    @Column
    private Date created_at;

    @ManyToOne
    private ProductOrder[] productOrders;

}
