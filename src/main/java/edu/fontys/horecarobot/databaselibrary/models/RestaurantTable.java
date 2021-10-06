package edu.fontys.horecarobot.databaselibrary.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "tables")
public class RestaurantTable {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column
    private int tableNumber;

    @Column
    private int xAxis;

    @Column
    private int yAxis;

    public RestaurantTable(int tableNumber, int x, int y) {
        this.tableNumber = tableNumber;
        this.xAxis = x;
        this.yAxis = y;
    }

    public RestaurantTable() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getXAxis() {
        return xAxis;
    }

    public void setXAxis(int x) {
        this.xAxis = x;
    }

    public int getYAxis() {
        return yAxis;
    }

    public void setYAxis(int y) {
        this.yAxis = y;
    }
}
