package edu.fontys.horecarobot.databaselibrary;

import edu.fontys.horecarobot.databaselibrary.models.Category;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.models.Tag;

import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Tag tag3 = new Tag("tag3");

        session.save(tag1);
        session.save(tag2);
        session.save(tag3);

        System.out.println("------");
        System.out.println(tag1);
        System.out.println(tag2);
        System.out.println(tag3);
        System.out.println("------");

        Tag tags[] = {tag1, tag2, tag3};

        Product testProd1 = new Product("name1", "image", 12.00, 12.00, "description", false, tags);
        Product testProd2 = new Product("name2", "image", 12.00, 12.00, "description", false, null);
        Product testProd3 = new Product("name3", "image", 12.00, 12.00, "description", false, null);
        Product testProd4 = new Product("name4", "image", 12.00, 12.00, "description", false, null);

        session.save(testProd1);
        session.save(testProd2);
        session.save(testProd3);
        session.save(testProd4);

        Product products[] = {testProd1, testProd2, testProd3, testProd4};

        Category subCat = new Category("sub-test-cat-1", "image-b64", null, true, null);
        session.save(subCat);
        Category parentCats[] = { subCat };
        
        Category cat = new Category("test-cat-1", "image-b64", parentCats, true, products);

        //Add new Employee object
        //Product product = new Product();
        session.save(cat);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    
        System.out.println("------");
        System.out.println(cat);
    }
}
