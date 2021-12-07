package edu.fontys.horecarobot.databaselibrary;

import edu.fontys.horecarobot.databaselibrary.models.Ingredient;
import edu.fontys.horecarobot.databaselibrary.models.IngredientProduct;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.models.Tag;
import edu.fontys.horecarobot.databaselibrary.repositories.IngredientProductRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.IngredientRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IngredientTests {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IngredientProductRepository ingredientProductRepository;

    private Product product;

    @BeforeAll
    public void setup() {
        product = new Product();
        product.setName("Test Product");
        product.setPrice(12);
        product = productRepository.saveAndFlush(product);
    }

    @Test
    public void create_ingredient_test() {
        var ingredient = new Ingredient(null, "Test Ingredient");
        ingredient = ingredientRepository.save(ingredient);

        Assertions.assertNotNull(ingredient.getId());
    }

    @Test
    public void add_ingredient_to_product_using_ingredient_product_repository() {
        var i = ingredientRepository.saveAndFlush(new Ingredient(null, "Product ingredient"));
        var p = productRepository.getById(product.getId());

        ingredientProductRepository.saveAndFlush(new IngredientProduct(null, p, i, true));

        p = productRepository.getById(product.getId());
        Assertions.assertFalse(p.getIngredients().isEmpty());
        Assertions.assertEquals(p.getIngredients().get(0).getIngredient().getId(), i.getId());
    }

    @Test
    public void add_ingredient_to_product_using_ingredients_property() {
        var i = ingredientRepository.saveAndFlush(new Ingredient(null, "Product ingredient 2"));
        var p = productRepository.getById(product.getId());

        Assertions.assertNotNull(i);
        Assertions.assertNotNull(p);
        p.setIngredients(List.of(new IngredientProduct(null, p, i, true)));

        productRepository.saveAndFlush(p);

        p = productRepository.getById(product.getId());
        Assertions.assertFalse(p.getIngredients().isEmpty());
        Assertions.assertEquals(p.getIngredients().get(0).getIngredient().getId(), i.getId());
    }

}
