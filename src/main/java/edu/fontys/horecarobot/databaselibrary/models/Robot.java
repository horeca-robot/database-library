package edu.fontys.horecarobot.databaselibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "robot")
public class Robot {

    // Id is not auto generated, id comes from socket API
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

}
