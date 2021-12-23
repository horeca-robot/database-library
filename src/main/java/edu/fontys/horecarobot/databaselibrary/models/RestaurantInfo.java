package edu.fontys.horecarobot.databaselibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "restaurant_info")
public class RestaurantInfo {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(name = "restaurant_logo")
    private String restaurantLogo;

    @Lob
    @Column(name = "background_image")
    private String backgroundImage;

    @Column(name = "primary_color")
    private String primaryColor;

    @Column(name = "secondary_color")
    private String secondaryColor;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "restaurant_id")
    private List<OpeningPeriod> openingTimes;

    @Column(name = "contact_person_name")
    private String contactPersonName;

    @Column(name = "contact_person_email")
    private String contactPersonEmail;

    @Column(name = "contact_person_phone")
    private String contactPersonPhone;

    @Column
    private String address;

    @Column
    private String province;

    @Column
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column
    private String country;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "kvk_number")
    private String kvkNumber;

    @Column(name = "tax_number")
    private String taxNumber;

}
