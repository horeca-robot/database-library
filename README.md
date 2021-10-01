# Example Main.java file for testing purposes

```java
package edu.fontys.horecarobot.databaselibrary;

import edu.fontys.horecarobot.databaselibrary.models.Product;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Insert a new product into the databsae
        Product product = new Product("Test", "image-b64", 62.32, 62.32, "Super cool description :D", true);
        session.save(product);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
```
