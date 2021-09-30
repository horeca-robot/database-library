package edu.fontys.horecarobot.databaselibrary.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String image;

    @Column(nullable = false)
    private double price;

    @Column(name = "discount-price")
    private double discountPrice;

    @Column
    private String description;

    @Column(name = "contains-alcohol")
    private boolean containsAlcohol;

}
