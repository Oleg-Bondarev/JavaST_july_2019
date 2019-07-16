package by.training.train.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.training.train.entity.carriage.CompartmentCarriage;
import by.training.train.entity.carriage.EconomClassCarriage;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.carriage.RestaurantCarriage;
import by.training.train.entity.carriage.SeatingCarriage;
import by.training.train.entity.enums.EconomClassEnum;
import by.training.train.entity.enums.ServiceEnum;
import by.training.train.repository.TrainRepository;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SortByIDSpecificationTest {
    List<PassengerCarriage> expectList;
    TrainRepository trainRepository;
    CompartmentCarriage compartmentCarriage;
    EconomClassCarriage economClassCarriage1;
    EconomClassCarriage economClassCarriage2;
    SeatingCarriage seatingCarriage;
    RestaurantCarriage restaurantCarriage;

    @BeforeMethod
    public void setUp() {
        trainRepository = TrainRepository.getInstance();
        trainRepository.getTrainCarriageList().clear();
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

        trainRepository.addPassengerCarriage(Optional.of(restaurantCarriage));
        trainRepository.addPassengerCarriage(Optional.of(seatingCarriage));
        trainRepository.addPassengerCarriage(Optional.of(economClassCarriage2));
        trainRepository.addPassengerCarriage(Optional.of(economClassCarriage1));
        trainRepository.addPassengerCarriage(Optional.of(compartmentCarriage));

        expectList = new ArrayList<>();
        expectList.add(compartmentCarriage);
        expectList.add(economClassCarriage1);
        expectList.add(economClassCarriage2);
        expectList.add(seatingCarriage);
        expectList.add(restaurantCarriage);
    }

    @Test(description = "Sorting carriages by ID from min to max ID")
    public void testSort() {
        SortByIDSpecification sortByIDSpecification = new SortByIDSpecification();
        List<PassengerCarriage> actualList = sortByIDSpecification.sort(trainRepository.getTrainCarriageList());

        assertEquals(actualList, expectList);
    }
}