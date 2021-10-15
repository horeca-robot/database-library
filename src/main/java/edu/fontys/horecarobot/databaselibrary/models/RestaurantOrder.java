package edu.fontys.horecarobot.databaselibrary.models;

import edu.fontys.horecarobot.databaselibrary.enums.DeliveryStatus;
import edu.fontys.horecarobot.databaselibrary.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;
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

    @Column
    private float subTotal;

    @Column
    private PaymentStatus paymentStatus;

    @Column
    private Date created_at;

    @Column
    private DeliveryStatus deliveryStatus;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

}
