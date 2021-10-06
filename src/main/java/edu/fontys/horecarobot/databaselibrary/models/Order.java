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
    @OrderColumn(name = "id")
    @JoinTable(
        name = "orders_tables",
        joinColumns = { @JoinColumn(table = "tables", referencedColumnName = "id", name = "table_id") },
        inverseJoinColumns = { @JoinColumn(table = "orders", referencedColumnName = "id", name = "order_id") }
    )
    private RestaurantTable table;

    public Order(){

    }

    public Order(float subTotal, PaymentStatus paymentStatus) {
        this.subTotal = subTotal;
        this.paymentStatus = paymentStatus;
        this.created_at = new Date();
    }

    public Order(float subTotal, PaymentStatus paymentStatus, RestaurantTable table) {
        this.subTotal = subTotal;
        this.paymentStatus = paymentStatus;
        this.created_at = new Date();
        this.table = table;
    }

    public String getId() {
        return this.id;
    }

    public float getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getCreated_at() {
        return this.created_at;
    }

    public RestaurantTable getTable() {
        return this.table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }    
}
