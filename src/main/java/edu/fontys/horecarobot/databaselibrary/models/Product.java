package edu.fontys.horecarobot.databaselibrary.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    private String image;

    @Column(nullable = true)
    private double price;

    @Column(name = "discount_price")
    private double discountPrice;

    @Column
    private String description;

    @Column(name = "contains_alcohol")
    private boolean containsAlcohol;

    @ElementCollection
    @ManyToMany(cascade = CascadeType.ALL)
    @OrderColumn(name = "id")
    @JoinTable(
        name = "products_tags",
        joinColumns = { @JoinColumn(table = "tags", referencedColumnName = "id", name = "tag_id") },
        inverseJoinColumns = { @JoinColumn(table = "products", referencedColumnName = "id", name = "product_id") }
    )
    private Tag tags[];

    public Product(String name, String image, double price, double discountPrice, String description, boolean containsAlcohol, Tag tags[]) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.discountPrice = discountPrice;
        this.description = description;
        this.containsAlcohol = containsAlcohol;
        this.tags = tags;
    }

    public String getId() {
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

    public Tag[] getTags() {
        return this.tags;
    }

    public void setTags(Tag tags[]) {
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
