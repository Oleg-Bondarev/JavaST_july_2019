package by.training.train.service.specification.find;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.training.train.dao.PassengerTrainRepository;
import by.training.train.entity.carriage.CompartmentCarriage;
import by.training.train.entity.carriage.EconomClassCarriage;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.carriage.RestaurantCarriage;
import by.training.train.entity.carriage.SeatingCarriage;
import by.training.train.entity.enums.EconomClassEnum;
import by.training.train.entity.enums.ServiceEnum;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.training.train.entity.enums.ServiceEnum.LUX;
import static org.testng.Assert.*;

public class FindByBaggageSpecificationTest {

    List<PassengerCarriage> expectList;
    PassengerTrainRepository trainRepository;
    CompartmentCarriage compartmentCarriage;
    EconomClassCarriage economClassCarriage1;
    EconomClassCarriage economClassCarriage2;
    SeatingCarriage seatingCarriage;
    RestaurantCarriage restaurantCarriage;

    @BeforeMethod
    public void setUp() {
        trainRepository = PassengerTrainRepository.getInstance();
        trainRepository.getTrainCarriageList().clear();
        compartmentCarriage =
                new CompartmentCarriage(2, LUX, 30, 11.0,
                        Boolean.valueOf("true"), 9);
        economClassCarriage1 =
                new EconomClassCarriage(2, ServiceEnum.ECONOM, 22, 20.0,
                        Boolean.valueOf("true"), 9,
                        EconomClassEnum.RESERVEDSEAT);
        economClassCarriage2 =
                new EconomClassCarriage(2, ServiceEnum.ECONOM, 22, 10.0,
                        Boolean.valueOf("true"), 9, EconomClassEnum.COMMON);
        seatingCarriage =
                new SeatingCarriage(1, ServiceEnum.BUISINESS,
                        30, 15.5, Boolean.valueOf("true"),
                        Boolean.valueOf("true"));
        restaurantCarriage =
                new RestaurantCarriage(6, ServiceEnum.BUISINESS, 0,
                        Boolean.valueOf("true"),
                        0, 48);

        trainRepository.addCarriage(Optional.of(compartmentCarriage));
        trainRepository.addCarriage(Optional.of(economClassCarriage1));
        trainRepository.addCarriage(Optional.of(economClassCarriage2));
        trainRepository.addCarriage(Optional.of(seatingCarriage));
        trainRepository.addCarriage(Optional.of(restaurantCarriage));

        expectList = new ArrayList<>();
    }

    @DataProvider(name = "FoundByBaggageDateProvider")
    public Object[][] createPositiveTest() {
        return new Object[][] {
                {2, 15.0, 20.0},
                {1, 0.0, 9.99999},
                {5, 0.0, 40.4},
                {1, 0.0, 0.0}
        };
    }

    @DataProvider(name = "NotFoundByBaggageDateProvider")
    public Object[][] createNegativeTest() {
        return new Object[][] {
                { -3.9, -0.999},
                { 40.6, 50.0}
        };
    }

    @Test(dataProvider = "NotFoundByBaggageDateProvider")
    public void notFound( double low, double upper) {
        FindByBaggageSpecification findByBaggageSpecification = new FindByBaggageSpecification(low, upper);
        List<PassengerCarriage> actualList = findByBaggageSpecification.find(trainRepository.getTrainCarriageList());
        assertEquals(actualList.size(), 0);
    }

    @Test(dataProvider = "FoundByBaggageDateProvider")
    public void testFound(int expect, double low, double upper) {
        FindByBaggageSpecification findByBaggageSpecification = new FindByBaggageSpecification(low, upper);
        List<PassengerCarriage> actualList = findByBaggageSpecification.find(trainRepository.getTrainCarriageList());
        assertEquals(actualList.size(), expect);
    }
}