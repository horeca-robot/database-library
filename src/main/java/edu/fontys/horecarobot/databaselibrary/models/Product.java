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
@Table(name = "product")
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

    @Lob
    @Column
    private String image;

    @Column(nullable = false)
    private double price;

    @Column(name = "discount_price")
    private double discountPrice;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(name = "contains_alcohol")
    private boolean containsAlcohol;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "product_tag",
        joinColumns = { @JoinColumn(table = "tag", referencedColumnName = "id", name = "tag_id") },
        inverseJoinColumns = { @JoinColumn(table = "product", referencedColumnName = "id", name = "product_id") }
    )
    private List<Tag> tags = new ArrayList<>();

    @OneToMany
    //@JoinColumn(name = "product_id")
    private List<IngredientProduct> ingredients;

    @ManyToMany
    @JoinTable(
            name = "category_product",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private List<Category> categories = new ArrayList<>();

}
