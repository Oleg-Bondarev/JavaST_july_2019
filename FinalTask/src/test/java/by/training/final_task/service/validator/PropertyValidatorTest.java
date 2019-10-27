package by.training.final_task.service.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PropertyValidatorTest {

    @DataProvider(name = "NegativeTestForProperty")
    public Object[][] createNegativeForProperty() {
        return new Integer[][] {
                {-1, 4, 3},
                {1, -1, 3},
                {1, 4, -1},
                {4, 1, 1}
        };
    }

    @Test(dataProvider = "NegativeTestForProperty", groups = {"Validator group"})
    public void testNegativeValidateProperty(final Integer[] arr) {
        boolean expect = PropertyValidator.isValidIntegerPropParameters(
                arr[0], arr[1], arr[2]);
        assertFalse(expect);
    }

    @DataProvider(name = "PositiveForProperty")
    public Object[][] createPositiveForProperty() {
        return new Integer[][] {
                {1, 3, 1}
        };
    }

    @Test(dataProvider = "PositiveForProperty", groups = {"Validator group"})
    public void testPositiveValidateForthProperty(final Integer[] arr) {
        boolean expect = PropertyValidator.isValidIntegerPropParameters(
                arr[0], arr[1], arr[2]);
        assertTrue(expect);
    }
}