package edu.fontys.horecarobot.databaselibrary.models;

import edu.fontys.horecarobot.databaselibrary.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "product_order")
public class ProductOrder {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_order_id", nullable = false)
    private Product product;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_order_product_by_product",
            joinColumns = { @JoinColumn(table = "product_order", referencedColumnName = "id", name = "product_order_id") },
            inverseJoinColumns = { @JoinColumn(table = "product", referencedColumnName = "id", name = "product_id") }
    )
    private List<Product> byProducts = new ArrayList<>();

}
