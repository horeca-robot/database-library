package edu.fontys.horecarobot.databaselibrary;

import edu.fontys.horecarobot.databaselibrary.models.Category;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.repositories.CategoryRepository;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductRepository;
import edu.fontys.horecarobot.databaselibrary.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryProductTests {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    private Category category;
    private Product product;

    @BeforeEach
    public void setup() {
        Category c = new Category(
                null,
                "Test Category",
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                true,
                new ArrayList<>()
        );
        Product p = new Product(
                null,
                "Test Product",
                null,
                12,
                12,
                "Test description",
                false,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        category = categoryRepository.saveAndFlush(c);
        product = productRepository.saveAndFlush(p);
    }

    @Test
    public void create_category_test() {
        Assertions.assertEquals(categoryRepository.findAll().size(), 1);
    }

    @Test
    public void create_product_test() {
        Assertions.assertEquals(productRepository.findAll().size(), 1);
    }

    @Test
    public void add_product_to_category_test() {
        product.setCategories(TestUtils.asList(category));

        productRepository.saveAndFlush(product);

        Assertions.assertEquals(1, categoryRepository.findAll().size());
        Assertions.assertEquals(1, productRepository.findAll().size());
        Assertions.assertEquals(1, productRepository.findAll().get(0).getCategories().size());
    }

    @Test
    public void delete_category_test() {
        add_product_to_category_test();

        var category = categoryRepository.findAll().get(0);
        categoryRepository.delete(category);
        categoryRepository.flush();

        Assertions.assertEquals(1, productRepository.count());
        Assertions.assertEquals(0, categoryRepository.count());
    }

    @Test
    public void delete_product_test() {
        add_product_to_category_test();

        var product = productRepository.findAll().get(0);
        productRepository.delete(product);
        productRepository.flush();

        Assertions.assertEquals(1, categoryRepository.count());
        Assertions.assertEquals(0, productRepository.count());
    }

}
