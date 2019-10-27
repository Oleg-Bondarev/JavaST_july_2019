package by.training.final_task.service.validator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class CouponParametersValidatorTest {
    private CouponParametersValidator couponParametersValidator =
            new CouponParametersValidator();
    private Map<String, Boolean> expectMap = new HashMap<>();
    private Map<String, Boolean> actualMap = new HashMap<>();

    @BeforeMethod
    public void clearMap() {
        expectMap.clear();
        actualMap.clear();
    }

    @DataProvider(name = "NegativeTestForFirstCouponParameter")
    public Object[] createNegativeForFirstCouponParameter() {
        return new String[][] {
                {"Name1'", "Description", "123.20", "Address 4, street 5"},
                {"<script>Name1</script>", "Description", "123.20", "Address 4, street 5"},
                {"admin' or '1'='1", "Description", "123.20", "Address 4, street 5"},
                {"Name// group", "Description", "123.20", "Address 4, street 5"}
        };
    }

    @Test(dataProvider = "NegativeTestForFirstCouponParameter", groups = {"Validator group"})
    public void testNegativeValidateFirst(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectCouponName", true);
        expectMap.put("incorrectDescription", false);
        expectMap.put("incorrectPrice", false);
        expectMap.put("incorrectAddress", false);

        actualMap = couponParametersValidator.validate(testList);
        assertEquals(actualMap, expectMap);
    }

    @DataProvider(name = "NegativeTestForSecondCouponParameter")
    public Object[] createNegativeForSecondCouponParameter() {
        return new String[][]{
                {"Name1", "Description'", "123.20", "Address 4, street 5"},
                {"Name1", "<script>Description</script>", "123.20", "Address 4, street 5"},
                {"Name1", "admin' or '1'='1", "123.20", "Address 4, street 5"},
                {"Name1", "Description //", "123.20", "Address 4, street 5"}
        };
    }

    @Test(dataProvider = "NegativeTestForSecondCouponParameter", groups = {"Validator group"})
    public void testNegativeValidateSecond(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectCouponName", false);
        expectMap.put("incorrectDescription", true);
        expectMap.put("incorrectPrice", false);
        expectMap.put("incorrectAddress", false);

        actualMap = couponParametersValidator.validate(testList);
        assertEquals(actualMap, expectMap);
    }

    @DataProvider(name = "NegativeTestForThirdCouponParameter")
    public Object[] createNegativeForThirdCouponParameter() {
        return new String[][]{
                {"Name1", "Description", "123.20'", "Address 4, street 5"},
                {"Name1", "Description", "<script>123.20</script>", "Address 4, street 5"},
                {"Name1", "Description", "admin' or '1'='1", "Address 4, street 5"},
                {"Name1", "Description", "023.20", "Address 4, street 5"},
                {"Name1", "Description", "23.201", "Address 4, street 5"},
                {"Name1", "Description", "23?20", "Address 4, street 5"},
                {"Name1", "Description", "23,20", "Address 4, street 5"}
        };
    }

    @Test(dataProvider = "NegativeTestForThirdCouponParameter", groups = {"Validator group"})
    public void testNegativeValidateThird(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectCouponName", false);
        expectMap.put("incorrectDescription", false);
        expectMap.put("incorrectPrice", true);
        expectMap.put("incorrectAddress", false);

        actualMap = couponParametersValidator.validate(testList);
        assertEquals(actualMap, expectMap);
    }

    @DataProvider(name = "NegativeTestForForthCouponParameter")
    public Object[] createNegativeForForthCouponParameter() {
        return new String[][]{
                {"Name1", "Description", "123.20", "Address 4', street 5"},
                {"Name1", "Description", "123.20", "<script>Address 4</script>"},
                {"Name1", "Description", "123.20", "<script>allert('Hello')</script>"},
                {"Name1", "Description", "123.20", "admin' or '1'='1"},
                {"Name1", "Description", "123.20", "Address 4, street 5//"}
        };
    }

    @Test(dataProvider = "NegativeTestForForthCouponParameter", groups = {"Validator group"})
    public void testNegativeValidateForth(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectCouponName", false);
        expectMap.put("incorrectDescription", false);
        expectMap.put("incorrectPrice", false);
        expectMap.put("incorrectAddress", true);

        actualMap = couponParametersValidator.validate(testList);
        assertEquals(actualMap, expectMap);
    }

    @DataProvider(name = "PositiveTestForCouponParameter")
    public Object[] createPositiveForForthCouponParameter() {
        return new String[][]{
                {"Name1", "Description", "123.20", "Address 4, street 5"}
        };
    }

    @Test(dataProvider = "PositiveTestForCouponParameter", groups = {"Validator group"})
    public void testPositiveValidate(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectCouponName", false);
        expectMap.put("incorrectDescription", false);
        expectMap.put("incorrectPrice", false);
        expectMap.put("incorrectAddress", false);

        actualMap = couponParametersValidator.validate(testList);
        assertEquals(actualMap, expectMap);
    }
}