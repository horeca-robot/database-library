package edu.fontys.horecarobot.databaselibrary;

import edu.fontys.horecarobot.databaselibrary.models.Product;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //Add new Employee object
        //Product product = new Product();
        //session.save(product);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
