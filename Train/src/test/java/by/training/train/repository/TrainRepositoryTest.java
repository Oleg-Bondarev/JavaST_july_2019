package by.training.train.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.training.train.entity.carriage.CompartmentCarriage;
import by.training.train.entity.carriage.EconomClassCarriage;
import by.training.train.entity.carriage.RestaurantCarriage;
import by.training.train.entity.carriage.SeatingCarriage;
import by.training.train.entity.carriage.TrainCarriage;
import by.training.train.entity.enums.EconomClassEnum;
import by.training.train.entity.enums.ServiceEnum;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TrainRepositoryTest {
    List<TrainCarriage> expectRepository;
    CompartmentCarriage compartmentCarriage;
    EconomClassCarriage economClassCarriage1;
    EconomClassCarriage economClassCarriage2;
    SeatingCarriage seatingCarriage;
    RestaurantCarriage restaurantCarriage;

    @BeforeMethod(groups = {"Repository"}, description = "Set up")
    public void setUp() {
        compartmentCarriage =
                new CompartmentCarriage(2, ServiceEnum.ECONOM,
    40.5, Boolean.valueOf("false"), 9);
        economClassCarriage1 =
                new EconomClassCarriage(2, ServiceEnum.ECONOM,
    20.0, Boolean.valueOf("true"), 9, EconomClassEnum.RESERVEDSEAT);
         economClassCarriage2 =
                new EconomClassCarriage(2, ServiceEnum.ECONOM,
    10.0, Boolean.valueOf("false"), 9, EconomClassEnum.COMMON);
        seatingCarriage =
                new SeatingCarriage(1, ServiceEnum.BUISINESS,
  30, 15.5, Boolean.valueOf("true"), Boolean.valueOf("true"));
        restaurantCarriage =
                new RestaurantCarriage(6, ServiceEnum.BUISINESS, 0, Boolean.valueOf("true"),
                0, 48);
        expectRepository = new ArrayList<>();
        expectRepository.add(compartmentCarriage);
        expectRepository.add(economClassCarriage1);
        expectRepository.add(economClassCarriage2);
        expectRepository.add(seatingCarriage);
        expectRepository.add(restaurantCarriage);
    }

    @Test(groups = {"Repository"}, description = "Testing of add carriage in list")
    public void testAddPassengerCarriage() {
        TrainRepository actualRepository = TrainRepository.getInstance();
        actualRepository.addPassengerCarriage(Optional.of(compartmentCarriage));
        actualRepository.addPassengerCarriage(Optional.of(economClassCarriage1));
        actualRepository.addPassengerCarriage(Optional.of(economClassCarriage2));
        actualRepository.addPassengerCarriage(Optional.of(seatingCarriage));
        actualRepository.addPassengerCarriage(Optional.of(restaurantCarriage));

        assertEquals(actualRepository.getTrainCarriageList(), expectRepository);
    }

    @Test
    public void testRemovePassengerCarriage() {
        expectRepository.remove(restaurantCarriage);

        TrainRepository actualRepository = TrainRepository.getInstance();
        actualRepository.addPassengerCarriage(Optional.of(compartmentCarriage));
        actualRepository.addPassengerCarriage(Optional.of(economClassCarriage1));
        actualRepository.addPassengerCarriage(Optional.of(economClassCarriage2));
        actualRepository.addPassengerCarriage(Optional.of(seatingCarriage));
        actualRepository.addPassengerCarriage(Optional.of(restaurantCarriage));

        actualRepository.removePassengerCarriage(Optional.of(restaurantCarriage));

        assertEquals(actualRepository.getTrainCarriageList(), expectRepository);
    }

    @Test
    public void testQueryPassengerCarriage() {
    }

    @Test
    public void testReplacePassengerCarriage() {
    }
}