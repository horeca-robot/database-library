package edu.fontys.horecarobot.databaselibrary.models;

import edu.fontys.horecarobot.databaselibrary.enums.PaymentStatus;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    @Column
    private float subTotal;

    @Column
    private PaymentStatus paymentStatus;

    @Column
    private Date created_at;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderColumn(name = "id")
    @JoinColumn(name = "table_id")
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

    public UUID getId() {
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

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", subTotal='" + getSubTotal() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", created_at='" + getCreated_at() + "'" +
            ", table='" + getTable() + "'" +
            "}";
    }
}
