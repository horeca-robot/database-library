# How to add this library to your project
**Step 1. Add the JitPack repository to your build file**
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
**Step 2. Add the dependency**
```xml
<dependency>
    <groupId>com.github.horeca-robot</groupId>
    <artifactId>database-library</artifactId>
    <version>1.4</version>
</dependency>
```
**Step 3. Add Entity/Component scans to your project to make the models/repositories visible for Spring**
```java
@SpringBootApplication
@EntityScan("edu.fontys.horecarobot.databaselibrary.models")
@EnableJpaRepositories("edu.fontys.horecarobot.databaselibrary.repositories")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```
**Step 4. Adding a database to your project**

To do this you can follow this guide: https://spring.io/guides/gs/accessing-data-mysql/
But skipping the 'Create the @Entity Model' 'Create the Repository' steps as these are included in this library.

# Example of how to use the models/repositories

```java
import edu.fontys.horecarobot.databaselibrary.enums.PaymentStatus;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantOrder;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final RestaurantOrderRepository orderRepository;

    public ExampleService(RestaurantOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<RestaurantOrder> getAllOrders() {
        orderRepository.findAll();
    }

    public void createTestOrder() {
        RestaurantOrder restaurantOrder = new RestaurantOrder();
        restaurantOrder.setPaymentStatus(PaymentStatus.FAILED);
        restaurantOrder.setSubTotal(100.65f);
        orderRepository.saveAndFlush(restaurantOrder);
    }

}
```
