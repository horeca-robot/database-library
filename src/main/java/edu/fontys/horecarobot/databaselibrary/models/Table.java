package edu.fontys.horecarobot.databaselibrary.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "Tables")
public class Table {
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

    public Table(int tableNumber, int x, int y) {
        this.tableNumber = tableNumber;
        this.xAxis = x;
        this.yAxis = y;
    }

    public Table() {

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
