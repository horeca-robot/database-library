package edu.fontys.horecarobot.databaselibrary.models;

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
@Table(name = "products")
public class Product {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String image;

    @Column(nullable = true)
    private double price;

    @Column(name = "discount_price")
    private double discountPrice;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(name = "contains_alcohol")
    private boolean containsAlcohol;

    @ManyToMany(cascade = CascadeType.ALL)
    @OrderColumn(name = "id")
    @JoinTable(
        name = "products_tags",
        joinColumns = { @JoinColumn(table = "tags", referencedColumnName = "id", name = "tag_id") },
        inverseJoinColumns = { @JoinColumn(table = "products", referencedColumnName = "id", name = "product_id") }
    )
    private List<Tag> tags = new ArrayList<>();

}
