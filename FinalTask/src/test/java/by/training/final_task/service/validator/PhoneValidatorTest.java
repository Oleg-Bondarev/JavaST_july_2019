package by.training.final_task.service.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class PhoneValidatorTest {

    @DataProvider(name = "NegativeTestPhone")
    public Object[] createNegativeForPhone() {
        return new String[] {
                "1234567890",
                "33649493'",
                "admin' or '1'='1",
                "33649493333",
                "3364s93333"
        };
    }

    @Test(dataProvider = "NegativeTestPhone", groups = {"Validator group"})
    public void testNegativeValidate(final String newString) {
        boolean expect = PhoneValidator.validate(newString);
        assertFalse(expect);
    }

    @DataProvider(name = "PositiveTestPhone")
    public Object[] createPositiveForPhone() {
        return new String[] {
                "293695421",
                "256458932",
                "334567890",
                "445196420"
        };
    }

    @Test(dataProvider = "PositiveTestPhone", groups = {"Validator group"})
    public void testPositiveValidate(final String newString) {
        boolean expect = PhoneValidator.validate(newString);
        assertTrue(expect);
    }
}