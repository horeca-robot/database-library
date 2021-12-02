package edu.fontys.horecarobot.databaselibrary.models;

import edu.fontys.horecarobot.databaselibrary.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "restaurant_order")
public class RestaurantOrder {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "sub_total")
    private double subTotal;

    @Column
    private boolean paid;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_order_id")
    private List<ProductOrder> productOrders = new ArrayList<>();

    @Transient
    private boolean orderDone;

    public boolean isOrderDone() {
        return  productOrders.stream().allMatch(order -> order.getOrderStatus() == OrderStatus.DELIVERED);
    }
}
