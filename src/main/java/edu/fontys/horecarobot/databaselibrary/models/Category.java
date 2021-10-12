package edu.fontys.horecarobot.databaselibrary.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "categories")
public class Category {
    
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;
    
    @Column(nullable = true)
    private String name;
    
    @Column
    private String image;

    @ManyToMany(cascade = CascadeType.ALL)
    @OrderColumn(name = "id")
    @JoinTable(
        name = "categories_categories",
        joinColumns = { @JoinColumn(table = "categories", referencedColumnName = "id", name = "parent_category_id") },
        inverseJoinColumns = { @JoinColumn(table = "category", referencedColumnName = "id", name = "category_id") }
    )
    @Column(name = "parent_category")
    private List<Category> parentCategory = new ArrayList<>();
    
    @Column
    private boolean visible;

    @ManyToMany(cascade = CascadeType.ALL)
    @OrderColumn(name = "id")
    @JoinTable(
        name = "categories_product",
        joinColumns = { @JoinColumn(table = "products", referencedColumnName = "id", name = "product_id") },
        inverseJoinColumns = { @JoinColumn(table = "category", referencedColumnName = "id", name = "category_id") }
    )
    private List<Product> products = new ArrayList<>();

}