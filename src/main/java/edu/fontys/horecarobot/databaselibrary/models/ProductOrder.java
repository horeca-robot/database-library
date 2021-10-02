package edu.fontys.horecarobot.databaselibrary.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productOrders")
public class ProductOrder {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column
    private boolean delivered;

    @Column
    private boolean deliverLater;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "productsOrders_products_byProducts",
            joinColumns = { @JoinColumn(table = "products", referencedColumnName = "id", name = "product_id") },
            inverseJoinColumns = { @JoinColumn(table = "productOrders", referencedColumnName = "id", name = "productOrder_id") }
    )
    private List<Product> byProducts = new ArrayList<>();


    public ProductOrder() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
