package edu.fontys.horecarobot.databaselibrary;

import edu.fontys.horecarobot.databaselibrary.enums.PaymentStatus;
import edu.fontys.horecarobot.databaselibrary.models.Order;
import edu.fontys.horecarobot.databaselibrary.models.RestaurantTable;

import org.hibernate.Session;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        RestaurantTable table = new RestaurantTable(UUID.randomUUID(), 1, 1, 2);
        session.save(table);

        Order order = new Order();
        order.setSubTotal(12.99f);
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setTable(table);
        session.save(order);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
