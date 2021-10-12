# How to add this library to your project
Step 1. Add the JitPack repository to your build file
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Step 2. Add the dependency
```xml
<dependency>
    <groupId>com.github.horeca-robot</groupId>
    <artifactId>database-library</artifactId>
    <version>1.4</version>
</dependency>
```
Step 3. Add Entity/Component scans to your project to make the models/repositories visible for Spring
```java
@SpringBootApplication
@EntityScan("edu.fontys.horecarobot.databaselibrary.models")
@ComponentScan("edu.fontys.horecarobot.databaselibrary.repositories")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```

# Example of how to use the repositories

```java
import edu.fontys.horecarobot.databaselibrary.models.Order;
import edu.fontys.horecarobot.databaselibrary.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public ExampleService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        orderRepository.findAll();
    }
    
}
```
