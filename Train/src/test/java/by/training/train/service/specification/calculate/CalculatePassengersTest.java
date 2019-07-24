package by.training.train.service.specification.calculate;

import java.util.Optional;

import by.training.train.dao.PassengerTrainRepository;
import by.training.train.entity.carriage.CompartmentCarriage;
import by.training.train.entity.carriage.EconomClassCarriage;
import by.training.train.entity.carriage.RestaurantCarriage;
import by.training.train.entity.carriage.SeatingCarriage;
import by.training.train.entity.enums.EconomClassEnum;
import by.training.train.entity.enums.ServiceEnum;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static by.training.train.entity.enums.ServiceEnum.LUX;
import static org.testng.Assert.*;

public class CalculatePassengersTest {

    PassengerTrainRepository trainRepository;
    CompartmentCarriage compartmentCarriage1, compartmentCarriage2;
    EconomClassCarriage economClassCarriage1;
    EconomClassCarriage economClassCarriage2;
    SeatingCarriage seatingCarriage1, seatingCarriage2;
    RestaurantCarriage restaurantCarriage;
    CalculatePassengers calculatePassengers = new CalculatePassengers();

    @BeforeMethod
    public void setUp() {
        trainRepository = PassengerTrainRepository.getInstance();
        trainRepository.getTrainCarriageList().clear();
        compartmentCarriage1 =
                new CompartmentCarriage(2, LUX, 30, 11.0,
                        Boolean.valueOf("true"), 9);
        compartmentCarriage2 =
                new CompartmentCarriage(2, LUX, 30, 4.0,
                        Boolean.valueOf("true"), 9);
        economClassCarriage1 =
                new EconomClassCarriage(2, ServiceEnum.ECONOM, 22, 20.0,
                        Boolean.valueOf("true"), 9,
                        EconomClassEnum.RESERVEDSEAT);
        economClassCarriage2 =
                new EconomClassCarriage(2, ServiceEnum.ECONOM, 22, 10.0,
                        Boolean.valueOf("true"), 9, EconomClassEnum.COMMON);
        seatingCarriage1 =
                new SeatingCarriage(1, ServiceEnum.BUISINESS,
                        30, 15.5, Boolean.valueOf("true"),
                        Boolean.valueOf("true"));
        seatingCarriage2 =
                new SeatingCarriage(1, ServiceEnum.BUISINESS,
                        30, 15.5, Boolean.valueOf("true"),
                        Boolean.valueOf("true"));
        restaurantCarriage =
                new RestaurantCarriage(6, ServiceEnum.BUISINESS, 0,
                        Boolean.valueOf("true"),
                        0, 48);

        trainRepository.addCarriage(Optional.of(compartmentCarriage1));
        trainRepository.addCarriage(Optional.of(compartmentCarriage2));
        trainRepository.addCarriage(Optional.of(economClassCarriage1));
        trainRepository.addCarriage(Optional.of(economClassCarriage2));
        trainRepository.addCarriage(Optional.of(seatingCarriage1));
        trainRepository.addCarriage(Optional.of(seatingCarriage2));
        trainRepository.addCarriage(Optional.of(restaurantCarriage));
    }

    @Test
    public void testCalculatePassengers() {
        int actual = calculatePassengers.calculatePassengers(Optional.of(trainRepository.getTrainCarriageList()));
        assertEquals(164, actual);
    }
}