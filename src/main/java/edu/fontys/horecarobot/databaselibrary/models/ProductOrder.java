package edu.fontys.horecarobot.databaselibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
            joinColumns = { @JoinColumn(table = "productOrders", referencedColumnName = "id", name = "productOrder_id") },
            inverseJoinColumns = { @JoinColumn(table = "products", referencedColumnName = "id", name = "product_id") }
    )
    private List<Product> byProducts = new ArrayList<>();

}
