package edu.fontys.horecarobot.databaselibrary;

import edu.fontys.horecarobot.databaselibrary.models.*;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantInfoRepository;
import edu.fontys.horecarobot.databaselibrary.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OpeningHoursTests {

    @Autowired
    private RestaurantInfoRepository restaurantInfoRepository;

    @BeforeAll
    public void setup() {
        var restaurantInfo = new RestaurantInfo();
        restaurantInfo.setName("Test Restaurant");
        restaurantInfoRepository.updateInfo(restaurantInfo);
    }

    @Test
    public void get_restaurant_info_test() {
        var restaurantInfo = restaurantInfoRepository.getInfo();

        Assertions.assertTrue(restaurantInfo.isPresent());
        Assertions.assertNotNull(restaurantInfo.get().getId());
    }

    @Test
    public void add_and_update_opening_period_test() {
        var restaurantInfo = restaurantInfoRepository.getInfo().get();
        LocalTime time = LocalTime.now();
        restaurantInfo.setOpeningTimes(TestUtils.asList(new OpeningPeriod(null, 1, time, time)));

        restaurantInfoRepository.saveAndFlush(restaurantInfo);
        restaurantInfo = restaurantInfoRepository.getInfo().get();

        Assertions.assertFalse(restaurantInfo.getOpeningTimes().isEmpty());

        restaurantInfo.getOpeningTimes().get(0).setDayOfWeek(4);
        restaurantInfoRepository.saveAndFlush(restaurantInfo);
        restaurantInfo = restaurantInfoRepository.getInfo().get();

        Assertions.assertEquals(restaurantInfo.getOpeningTimes().size(), 1);
        Assertions.assertEquals(restaurantInfo.getOpeningTimes().get(0).getDayOfWeek(), 4);
    }

}
