package edu.fontys.horecarobot.databaselibrary;

import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.repositories.ProductRepository;
import edu.fontys.horecarobot.databaselibrary.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ByProductTests {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setup() {
        var p = new Product();
        p.setName("Test Product");
        p.setPrice(12);
        product = productRepository.saveAndFlush(p);
    }

    @Test
    public void get_product_test() {
        Assertions.assertEquals(1, productRepository.count());
    }

    @Test
    public void add_by_product_to_product_test() {
        var byProduct = new Product();
        byProduct.setName("By Product");
        byProduct.setPrice(1);
        byProduct.setCanBeServedAsByProduct(true);

        byProduct = productRepository.saveAndFlush(byProduct);
        product.setByProducts(TestUtils.asList(byProduct));
        productRepository.saveAndFlush(product);

        Assertions.assertEquals(2, productRepository.count());
        Assertions.assertEquals(1, productRepository.getById(product.getId()).getByProducts().size());
        Assertions.assertEquals(0, productRepository.getById(byProduct.getId()).getByProducts().size());
    }

}
