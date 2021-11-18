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
@Table(name = "category")
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
    
    @Column(nullable = false)
    private String name;

    @Lob
    @Column
    private String image;

    @ManyToMany
    @JoinTable(
        name = "category_relations",
        joinColumns = { @JoinColumn(name = "child_category_id", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "parent_category_id", nullable = false) }
    )
    private List<Category> parentCategories = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "category_relations",
        joinColumns = { @JoinColumn(name = "parent_category_id", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "child_category_id", nullable = false) }
    )
    private List<Category> childCategories = new ArrayList<>();
    
    @Column
    private boolean visible;

    /**
     * For adding products to categories, you need to add the category to every product individually.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "category_product",
            joinColumns = { @JoinColumn(name = "category_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<Product> products = new ArrayList<>();

}
