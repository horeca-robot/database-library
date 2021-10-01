package edu.fontys.horecarobot.databaselibrary.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(nullable = true)
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
    
}
