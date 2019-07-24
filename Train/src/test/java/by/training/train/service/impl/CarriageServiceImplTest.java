package by.training.train.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.training.train.entity.carriage.CompartmentCarriage;
import by.training.train.entity.carriage.EconomClassCarriage;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.enums.EconomClassEnum;
import by.training.train.entity.enums.ServiceEnum;
import by.training.train.service.exception.ServiceException;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.CarriageService;
import by.training.train.service.interfaces.TrainService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CarriageServiceImplTest {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    CarriageService carriageService;
    TrainService trainService;
    List<PassengerCarriage> expectList;
    List<PassengerCarriage> actualList;
    List<String> inputInform;
    CompartmentCarriage compartmentCarriage =
            new CompartmentCarriage(3, ServiceEnum.LUX, 30, 11.0, true, 9);

    @BeforeMethod
    public void setUp() {
       carriageService = serviceFactory.getCarriageService();
       trainService = serviceFactory.getTrainService();
       trainService.getTrain().clear();
       expectList = new ArrayList<>();
       actualList = new ArrayList<>();
       inputInform = new ArrayList<>();
       expectList.add(new CompartmentCarriage(2,ServiceEnum.LUX,30,11.0,true,9));
       expectList.add(new EconomClassCarriage(2,ServiceEnum.ECONOM, 22,10.0,true, 9,EconomClassEnum.COMMON));
    }

    /*inputInform.add("COMPARTMENT,2,30,LUX,11.0,TRUE,9");
       inputInform.add("ECONOMCLASS,2,22,ECONOM,20.0,true,9,RESERVEDSEAT,SOMETHING");
       inputInform.add("ECONOMCLASS,2,22,ECONOM,10.0,TRUE,9,COMMON");
       inputInform.add("SEATING,-11,30,BUISINESS,15.5,TRUE");
       inputInform.add("COMPARTMENT,3,30,LUX,11.0,TRUE,9");*/
    @AfterMethod
    public void afterM() {
        expectList = null;
        inputInform = null;
    }

    @Test
    public void testAddCarriage() throws ServiceException {
        carriageService.addCarriage("COMPARTMENT,2,30,LUX,11.0,TRUE,9");
        carriageService.addCarriage("ECONOMCLASS,2,22,ECONOM,10.0,TRUE,9,COMMON");

        actualList = trainService.getTrain();
        assertEquals(actualList, expectList);
    }

    @DataProvider(name = "ArrayWithSomeIncorrectParameters")
    public Object[] createTestForException() {
        return new String[] {
                "ECONOMCLASS,2,22,ECONOM,20.0,true,9,RESERVEDSEAT,SOMETHING",
                "SEATING,-11,30,BUISINESS,15.5,TRUE"
        };
    }

    @Test(expectedExceptions = ServiceException.class,
          dataProvider = "ArrayWithSomeIncorrectParameters")
    public void testAddCarriageExpectException(String str) throws ServiceException {
        carriageService.addCarriage(str);
    }


    @Test()
    public void testRemoveCarriage() throws ServiceException {
        carriageService.addCarriage("COMPARTMENT,2,30,LUX,11.0,TRUE,9");
        carriageService.addCarriage("ECONOMCLASS,2,22,ECONOM,10.0,TRUE,9,COMMON");
        carriageService.addCarriage("COMPARTMENT,3,30,LUX,11.0,TRUE,9");
        carriageService.removeCarriage(compartmentCarriage);
        List<PassengerCarriage> lp = trainService.getTrain();
        assertEquals(lp, expectList);
    }

    @Test
    public void testQuery() {
    }
}