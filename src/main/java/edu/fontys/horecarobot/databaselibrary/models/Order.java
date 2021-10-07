package edu.fontys.horecarobot.databaselibrary.models;

import edu.fontys.horecarobot.databaselibrary.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

}
