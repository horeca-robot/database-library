package edu.fontys.horecarobot.databaselibrary;

import edu.fontys.horecarobot.databaselibrary.models.*;
import edu.fontys.horecarobot.databaselibrary.repositories.IngredientRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantInfoRepository;
import edu.fontys.horecarobot.databaselibrary.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.time.LocalTime;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductIngredientTests {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private EntityManager entityManager;

    private Ingredient testIngredient;

    @BeforeEach
    public void setup() {
        testIngredient = new Ingredient();
        testIngredient.setName("Test Ingredient");
        testIngredient = ingredientRepository.saveAndFlush(testIngredient);
    }

    @Test
    public void create_ingredient_test() {
        var ingredient = new Ingredient();
        ingredient.setName("Create Ingredient Test");

        ingredientRepository.saveAndFlush(ingredient);

        var optional = ingredientRepository.findOneByName("Create Ingredient Test");
        Assertions.assertTrue(optional.isPresent());
    }

    @Test
    public void add_ingredient_to_new_product() {
        var product = new Product();
        product.setName("Test Product");
        product.setPrice(12);
        product.setIngredients(TestUtils.asList(new IngredientProduct(null, product, testIngredient, true)));

        productRepository.saveAndFlush(product);
        entityManager.clear();

        var optional = productRepository.findOneByName("Test Product");
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(1, optional.get().getIngredients().size());
        Assertions.assertNotNull(optional.get().getIngredients().get(0).getIngredient());
        Assertions.assertEquals("Test Ingredient", optional.get().getIngredients().get(0).getIngredient().getName());
    }

    @Test
    public void add_ingredient_to_existing_product() {
        var product = new Product();
        product.setName("Test Product");
        product.setPrice(12);

        product = productRepository.saveAndFlush(product);

        product.setIngredients(TestUtils.asList(new IngredientProduct(null, product, testIngredient, true)));

        productRepository.saveAndFlush(product);
        entityManager.clear();

        var optional = productRepository.findOneByName("Test Product");
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(1, optional.get().getIngredients().size());
        Assertions.assertNotNull(optional.get().getIngredients().get(0).getIngredient());
        Assertions.assertNotNull(optional.get().getIngredients().get(0).getIngredient().getId());
        Assertions.assertEquals("Test Ingredient", optional.get().getIngredients().get(0).getIngredient().getName());
    }

}
