package edu.fontys.horecarobot.databaselibrary.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
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

    public Product(String name, String image, double price, double discountPrice, String description, boolean containsAlcohol, List<Tag> tags) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.discountPrice = discountPrice;
        this.description = description;
        this.containsAlcohol = containsAlcohol;
        this.tags = tags;
    }

    public Product(String name, String image, double price, double discountPrice, String description, boolean containsAlcohol) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.discountPrice = discountPrice;
        this.description = description;
        this.containsAlcohol = containsAlcohol;
    }

    public Product() {

    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getContainsAlcohol() {
        return this.containsAlcohol;
    }

    public void setContainsAlcohol(boolean containsAlcohol) {
        this.containsAlcohol = containsAlcohol;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", image='" + getImage() + "'" +
            ", price='" + getPrice() + "'" +
            ", discountPrice='" + getDiscountPrice() + "'" +
            ", description='" + getDescription() + "'" +
            ", containsAlcohol='" + getContainsAlcohol() + "'" +
            ", tags[]='" + getTags() + "'" +
            "}";
    }
}
