package edu.fontys.horecarobot.databaselibrary.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "categories")
public class Category {
    
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
    
    @ElementCollection
    @ManyToMany(cascade = CascadeType.ALL)
    @OrderColumn(name = "id")
    @JoinTable(
        name = "categories_categories",
        joinColumns = { @JoinColumn(table = "categories", referencedColumnName = "id", name = "parent_category_id") },
        inverseJoinColumns = { @JoinColumn(table = "category", referencedColumnName = "id", name = "category_id") }
    )
    @Column(name = "parent_category")
    private Category[] parentCategory;
    
    @Column
    private boolean visible;

    @ElementCollection
    @ManyToMany(cascade = CascadeType.ALL)
    @OrderColumn(name = "id")
    @JoinTable(
        name = "categories_product",
        joinColumns = { @JoinColumn(table = "products", referencedColumnName = "id", name = "product_id") },
        inverseJoinColumns = { @JoinColumn(table = "category", referencedColumnName = "id", name = "category_id") }
    )
    private Product[] products;


    public Category(String name, String image, Category[] parentCategory, boolean visible, Product[] products) {
        this.name = name;
        this.image = image;
        this.parentCategory = parentCategory;
        this.visible = visible;
        this.products = products;
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

    public Category[] getParentCategory() {
        return this.parentCategory;
    }

    public void setParentCategory(Category[] parentCategory) {
        this.parentCategory = parentCategory;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Product[] getProducts() {
        return this.products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", image='" + getImage() + "'" +
            ", parentCategory='" + getParentCategory() + "'" +
            ", visible='" + isVisible() + "'" +
            ", products='" + getProducts() + "'" +
            "}";
    }
}
