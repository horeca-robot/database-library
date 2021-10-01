package edu.fontys.horecarobot.databaselibrary.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ProductOrder {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    @Column
    private boolean delivered;

    @Column
    private boolean deliverLater;

    public ProductOrder() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isDeliverLater() {
        return deliverLater;
    }

    public void setDeliverLater(boolean deliverLater) {
        this.deliverLater = deliverLater;
    }

    public ProductOrder(Product product, Order order) {
        this.product = product;
        this.order = order;
        this.delivered = false;
        this.deliverLater = false;
    }
}