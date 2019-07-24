package by.training.train.controller;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ControllerTest {

    Controller controller = new Controller();

    @DataProvider(name = "NegativeDateProviderForValidateRequest")
    public Object[] createNegativeForValidateRequest() {
        return new String[] {
                null,
                ""
        };
    }

    @Test(dataProvider = "NegativeDateProviderForValidateRequest")
    public void testExecuteeRequestNegative(String str) {
        String expect = "Incorrect request or unknown command.";
        String actual = controller.executeeRequest(str);
        assertEquals(actual, expect);
    }
}