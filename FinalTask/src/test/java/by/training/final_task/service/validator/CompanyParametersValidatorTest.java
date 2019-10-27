package by.training.final_task.service.validator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class CompanyParametersValidatorTest {
    private CompanyParametersValidator companyParametersValidator =
            new CompanyParametersValidator();
    private Validator validator = new Validator();
    private Map<String, Boolean> expectMap = new HashMap<>();
    private Map<String, Boolean> actualMap = new HashMap<>();

    @BeforeMethod
    public void clearMap() {
        expectMap.clear();
        actualMap.clear();
    }

    @DataProvider(name = "NegativeTestForFirstCompanyParameter")
    public Object[] createNegativeForFirstCompanyParameter() {
        return new String[][] {
                {"Name1'", "Address 4, street 5", "336494933"},
                {"<script>Name1</script>", "Address 4, street 5", "336494933"},
                {"admin' or '1'='1", "Address 4, street 5", "336494933"},
                {"Name// group", "Address 4, street 5", "336494933"}
        };
    }

    @Test(dataProvider = "NegativeTestForFirstCompanyParameter", groups = {"Validator group"})
    public void testNegativeValidateFirst(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectName", true);
        expectMap.put("incorrectAddress", false);
        expectMap.put("incorrectPhone", false);
        actualMap = companyParametersValidator.validate(testList);
        assertEquals(expectMap, actualMap);
    }

    @DataProvider(name = "NegativeTestForSecondCompanyParameter")
    public Object[] createNegativeForSecondCompanyParameter() {
        return new String[][]{
                {"Name1", "Street' 1", "336494933"},
                {"Name1", "<script>Street 1</script>", "336494933"},
                {"Name1", "admin' or '1'='1", "336494933"},
                {"Name1", "Street 1 ,//house 4", "336494933"}
        };
    }

    @Test(dataProvider = "NegativeTestForSecondCompanyParameter", groups = {"Validator group"})
    public void testNegativeValidateSecond(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectName", false);
        expectMap.put("incorrectAddress", true);
        expectMap.put("incorrectPhone", false);
        actualMap = companyParametersValidator.validate(testList);
        assertEquals(expectMap, actualMap);
    }

    @DataProvider(name = "NegativeTestForThirdCompanyParameter")
    public Object[] createNegativeForThirdCompanyParameter() {
        return new String[][]{
                {"Name1", "Street 1", "1234567890"},
                {"Name1", "Street 1", "33649493'"},
                {"Name1", "Street 1", "admin' or '1'='1"},
                {"Name1", "Street 1", "33649493333"},
                {"Name1", "Street 1", "3364s93333"}
        };
    }

    @Test(dataProvider = "NegativeTestForThirdCompanyParameter", groups = {"Validator group"})
    public void testNegativeValidateThird(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectName", false);
        expectMap.put("incorrectAddress", false);
        expectMap.put("incorrectPhone", true);
        actualMap = companyParametersValidator.validate(testList);
        assertEquals(expectMap, actualMap);
    }

    @DataProvider(name = "PositiveTestCompanyParameter")
    public Object[] createPositiveCompanyParameter() {
        return new String[][]{
                {"Name1", "Street 1", "336494933"}
        };
    }

    @Test(dataProvider = "PositiveTestCompanyParameter", groups = {"Validator group"})
    public void testPositiveValidate(final String[] newStringsArr) {
        List<String> testList = Arrays.asList(newStringsArr);
        expectMap.put("incorrectName", false);
        expectMap.put("incorrectAddress", false);
        expectMap.put("incorrectPhone", false);
        actualMap = companyParametersValidator.validate(testList);
        assertEquals(expectMap, actualMap);
    }
}