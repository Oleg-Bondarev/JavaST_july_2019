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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FindByConditionerSpecificationTest {
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

        trainRepository.addPassengerCarriage(Optional.of(compartmentCarriage));
        trainRepository.addPassengerCarriage(Optional.of(economClassCarriage1));
        trainRepository.addPassengerCarriage(Optional.of(economClassCarriage2));
        trainRepository.addPassengerCarriage(Optional.of(seatingCarriage));
        trainRepository.addPassengerCarriage(Optional.of(restaurantCarriage));

        expectList = new ArrayList<>();
    }

    @Test
    public void testFind1() {
        FindByConditionerSpecification findByConditionerSpecification = new FindByConditionerSpecification(Boolean.TRUE);
        List<PassengerCarriage> actualList = findByConditionerSpecification.find(trainRepository.getTrainCarriageList());
        expectList.add(economClassCarriage1);
        expectList.add(seatingCarriage);
        expectList.add(restaurantCarriage);

        assertEquals(actualList, expectList);
    }

    @Test
    public void testFind2() {
        FindByConditionerSpecification findByConditionerSpecification = new FindByConditionerSpecification(Boolean.FALSE);
        List<PassengerCarriage> actualList = findByConditionerSpecification.find(trainRepository.getTrainCarriageList());
        expectList.add(compartmentCarriage);
        expectList.add(economClassCarriage2);

        assertEquals(actualList, expectList);
    }

    @Test
    public void notFound() {
        trainRepository.getTrainCarriageList().remove(compartmentCarriage);
        trainRepository.getTrainCarriageList().remove(economClassCarriage2);
        FindByConditionerSpecification findByConditionerSpecification = new FindByConditionerSpecification(Boolean.FALSE);
        List<PassengerCarriage> actualList = findByConditionerSpecification.find(trainRepository.getTrainCarriageList());
        assertEquals(actualList.size(), 0);
    }
}