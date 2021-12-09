package edu.fontys.horecarobot.databaselibrary;

import edu.fontys.horecarobot.databaselibrary.models.*;
import edu.fontys.horecarobot.databaselibrary.repositories.RestaurantInfoRepository;
import edu.fontys.horecarobot.databaselibrary.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.time.LocalTime;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OpeningHoursTests {

    @Autowired
    private RestaurantInfoRepository restaurantInfoRepository;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
        var restaurantInfo = new RestaurantInfo();
        restaurantInfo.setName("Test Restaurant");
        restaurantInfoRepository.updateInfo(restaurantInfo);
        entityManager.clear();
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
        entityManager.clear();

        restaurantInfo = restaurantInfoRepository.getInfo().get();
        Assertions.assertEquals(1, restaurantInfo.getOpeningTimes().size());

        restaurantInfo.getOpeningTimes().get(0).setDayOfWeek(4);
        restaurantInfoRepository.saveAndFlush(restaurantInfo);
        entityManager.clear();

        restaurantInfo = restaurantInfoRepository.getInfo().get();
        Assertions.assertEquals(1, restaurantInfo.getOpeningTimes().size());
        Assertions.assertEquals(4, restaurantInfo.getOpeningTimes().get(0).getDayOfWeek());
    }

}
