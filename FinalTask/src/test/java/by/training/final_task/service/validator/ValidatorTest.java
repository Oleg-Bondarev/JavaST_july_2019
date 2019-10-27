package by.training.final_task.service.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ValidatorTest {
    private Validator validator = new Validator();

    @DataProvider(name = "NegativeTestForPrice")
    public Object[] createNegativeForPrice() {
        return new String[] {
                "012.21",
                "00.00",
                "-123.12",
                "sdf.12",
                "123.qw",
                "123,12"
        };
    }

    @Test(dataProvider = "NegativeTestForPrice", groups = {"Validator group"})
    public void testValidateNegativeCouponPrice(final String price) {
        boolean expect = validator.validateCouponPrice(price);
        assertFalse(expect);
    }

    @DataProvider(name = "PositiveTestForPrice")
    public Object[] createPositiveForPrice() {
        return new String[] {
                "123.12",
                "12.12",
                "1.20"
        };
    }

    @Test(dataProvider = "PositiveTestForPrice", groups = {"Validator group"})
    public void testValidatePositiveCouponPrice(final String price) {
        boolean expect = validator.validateCouponPrice(price);
        assertTrue(expect);
    }

    @DataProvider(name = "NegativeTestForRange")
    public Object[] createNegativeForRange() {
        return new String[][] {
                {"123.12", "20.11"},
                {"20.21", "20.20"}
        };
    }

    @Test(dataProvider = "NegativeTestForRange", groups = {"Validator group"})
    public void testValidateNegativeRange(final String[] price) {
        boolean expect = validator.validatePriceRange(price[0], price[1]);
        assertFalse(expect);
    }

    @DataProvider(name = "PositiveTestForRange")
    public Object[] createPositiveForRange() {
        return new String[][] {
                {"23.12", "209.11"},
                {"10.21", "20.20"}
        };
    }

    @Test(dataProvider = "PositiveTestForRange", groups = {"Validator group"})
    public void testValidatePositiveRange(final String[] price) {
        boolean expect = validator.validatePriceRange(price[0], price[1]);
        assertTrue(expect);
    }

    @DataProvider(name = "NegativeTestForIdParameter")
    public Object[] createNegativeForIdParameter() {
        return new String[] {
                "djf",
                "sd2",
                "1e2",
                "2df",
                "-12"
        };
    }

    @Test(dataProvider = "NegativeTestForIdParameter", groups = {"Validator group"})
    public void testValidateNegativeIdParameter(final String param) {
        boolean expect = validator.validateIdParameter(param);
        assertFalse(expect);
    }

    @DataProvider(name = "PositiveTestForIdParameter")
    public Object[] createPositiveForIdParameter() {
        return new String[] {
                "1",
                "10",
                "20",
                "09"
        };
    }

    @Test(dataProvider = "PositiveTestForIdParameter", groups = {"Validator group"})
    public void testValidatePositiveIdParameter(final String param) {
        boolean expect = validator.validateIdParameter(param);
        assertTrue(expect);
    }

}