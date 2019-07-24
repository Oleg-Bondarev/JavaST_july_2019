package by.training.train.validator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ValidatorTest {

    Validator validator = new Validator();

    @BeforeMethod(groups = {"Validator"}, description = "Set up")
    public void setUp() {

    }

    @DataProvider(name = "NegativeDateProviderForValidateStr")
    public Object[][] createNegativeForvalidateStr() {
        return new Object[][] {
                {false, "34rjh"},
                {false, "rg34kjh"},
                {false, "ergj34"},
                {false, ""}
        };
    }

    @DataProvider(name = "PositiveDateProviderForValidateStr")
    public Object[][] createPositiveForvalidateStr() {
        return new Object[][] { {true, "SITING"},
                {true, "VIP"},
                {true, "COMMON"}};
    }

    @DataProvider(name = "PositiveDateProviderForValidateInt")
    public Object[][] createPositiveForvalidateInt() {
        return new Object[][] { {true, "12"},
                {true, "1"},
                {true, "1000"},
                {true, "0"}};
    }

    @DataProvider(name = "NegativeDateProviderForValidateInt")
    public Object[][] createNegativeForvalidateInt() {
        return new Object[][] {
                {false, "-12"},
                {false, "jhfv"},
                {false, "jjk34"},
                {false, "21jbj"}
        };
    }

    @DataProvider(name = "PositiveDateProviderForValidateDouble")
    public Object[][] createPositiveForvalidateDouble() {
        return new Object[][] { {true, "12.5"},
                {true, "1.0"},
                {true, "1000.987"},
                {true, "0.09"}};
    }

    @DataProvider(name = "NegativeDateProviderForValidateDouble")
    public Object[][] createNegativeForvalidateDouble() {
        return new Object[][] {
                {false, "-12.09"},
                {false, "jhfv"},
                {false, "jjk34.6"},
                {false, "21.09jbj"}
        };
    }

    @DataProvider(name = "PositiveDateProviderForValidateBoolean")
    public Object[][] createPositiveForvalidateBoolean() {
        return new Object[][] { {true, "TRUE"},
                {true, "true"},
                {true, "TrUe"},
                {true, "FALSE"},
                {true, "false"},
                {true, "fAlSe"}};
    }

    @DataProvider(name = "NegativeDateProviderForValidateBoolean")
    public Object[][] createNegativeForvalidateBoolean() {
        return new Object[][] {
                {false, "-false"},
                {false, "False34"},
                {false, "true34.6"},
                {false, "21.09TRUE"}
        };
    }

    @DataProvider(name = "PositiveDateProviderForPassengerCarriage")
    public Object[][] createPositiveForPassengerCarriage() {
        String[][] array = new String[][] {
                {"COMPARTMENT", "1", "58", "LUX", "32.5", "TRUE"},
                {"Seating", "2", "29", "buisiness", "12", "true"},
                {"Restaurant", "10", "44", "ECONOM", "0", "false"}};
        return new Object[][] {
                {true, array[0]},
                {true, array[1]},
                {true, array[2]}};
    }

    @DataProvider(name = "NegativeDateProviderForPassengerCarriage")
    public Object[][] createNegativeForPassengerCarriage() {
        String[][] array = new String[][] {
                {"1", "58", "LUX", "32.5", "TRUE"},
                {"Siting", "e2", "49", "buisness", "12", "true"},
                {"Restaurant", "10", "44n1", "ECONOM", "0", "false"},
                {"Restaurant", "10", "44n1", "ECONOM007", "01010", "false"},
                {"Restaurant", "10", "44n1", "ECONOM", "0", "falsefalse"},
                {"Post", "10", "44n1", "ECONOM", "0", "falsefalse"}};
        return new Object[][] {
                {false, array[0]},
                {false, array[1]},
                {false, array[3]},
                {false, array[4]},
                {false, array[5]}
        };
    }

    @DataProvider(name = "PositiveDateProviderForCompartmentCarriage")
    public Object[][] createPositiveForCompartmentCarriage() {
        String[][] array = new String[][] {
                {"COMPARTMENT","1","36","LUX","32.5","TRUE","9"},
                {"COMPARTMENT","2","16","vip","12","true","4"}};
        return new Object[][] {
                {true, array[0]},
                {true, array[1]}};
    }

    @DataProvider(name = "NegativeDateProviderForCompartmentCarriage")
    public Object[][] createNegativeForCompartmentCarriage() {
        String[][] array = new String[][] {
                {"COMPARTMENT","1","36","LUX","32.5","TRUE","ef3"},
                {"COMPARTMENT","1","36","LUX","32.5","TRUE","9ef"},
                {"COMPARTMENT","1","36","LUX","32.5","TRUE","0"},
                {"COMPARTMENT","1","36","LUX","50.9","TRUE","9"},
                {"COMPARTMENT","1","36","LUX","32.5","TRUE","10"},
                {"COMPARTMENT","1","36","LUX","32.5","TRUE","10","something"}};
        return new Object[][] {
                {false, array[0]},
                {false, array[1]},
                {false, array[2]},
                {false, array[3]},
                {false, array[4]},
                {false, array[5]}
        };
    }

    @DataProvider(name = "PositiveDateProviderForEconomClassCarriage")
    public Object[][] createPositiveForEconomClassCarriage() {
        String[][] array = new String[][] {
                {"ECONOMCLASS","1","36","LUX","32.5","TRUE","9","RESERVEDSEAT"},
                {"ECONOMCLASS","2","54","vip","12.0","false","9","COMMON"}};
        return new Object[][] {
                {true, array[0]},
                {true, array[1]}};
    }

    @DataProvider(name = "NegativeDateProviderForEconomClassCarriage")
    public Object[][] createNegativeForEconomClassCarriage() {
        String[][] array = new String[][] {
                {"ECONOMCLASS","1","36","LUX","50.5","TRUE","9","RESERVEDSEAT"},
                {"ECONOMCLASS","1","36","LUX","50.5","sfg","9","RESERVEDSEAT"},
                {"ECONOMCLASS","1","36","LUX","50.5","TRUE","9ef","RESERVEDSEAT"},
                {"ECONOMCLASS","1","36","LUX","50.5","TRUE","11","RESERVEDSEAT"},
                {"ECONOMCLASS","1","36","LUX","50.5","TRUE","9","RESERVEDSEAT"},
                {"ECONOMCLASS","1","36","LUX","50.5","TRUE","9","common","something"}};
        return new Object[][] {
                {false, array[0]},
                {false, array[1]},
                {false, array[2]},
                {false, array[3]},
                {false, array[4]},
                {false, array[5]}
        };
    }

    @DataProvider(name = "PositiveDateProviderForSeatingCarriage")
    public Object[][] createPositiveForSeatingCarriage() {
        String[][] array = new String[][] {
                {"SEATING","1","36","LUX","30.5","TRUE","TRUE"},
                {"SEATING","2","54","vip","12","false","false"}};
        return new Object[][] {
                {true, array[0]},
                {true, array[1]}};
    }

    @DataProvider(name = "NegativeDateProviderForSeatingCarriage")
    public Object[][] createNegativeForSeatingCarriage() {
        String[][] array = new String[][] {
                {"SEATING","1","36","LUX","50.5","TRUE","TRUE"},
                {"SEATING","1","36","LUX","50.5","sfg","true"},
                {"SEATING","1","36","LUX","50.5","TRUE","false9ef"},
                {"SEATING","1","36","LUX","50.5","TRUE","11"},
                {"SEATING","1","36","LUX","50.5","TRUE","9","RESERVED"}
                };
        return new Object[][] {
                {false, array[0]},
                {false, array[1]},
                {false, array[2]},
                {false, array[3]},
                {false, array[4]}
        };
    }

    @DataProvider(name = "PositiveDateProviderForRestaurantCarriage")
    public Object[][] createPositiveForRestaurantCarriage() {
        String[][] array = new String[][] {
                {"RESTAURANT","13","0","LUX","0","TRUE","54"},
                {"RESTAURANT","12","0","vip","0","false","50"}};
        return new Object[][] {
                {true, array[0]},
                {true, array[1]}};
    }

    @DataProvider(name = "NegativeDateProviderForRestaurantCarriage")
    public Object[][] createNegativeForRestaurantCarriageCarriage() {
        String[][] array = new String[][] {
                {"RESTAURANT","13","2","LUX","0","TRUE","54"},
                {"RESTAURANT","13","0","LUX","6","TRUE","54"},
                {"RESTAURANT","13","0","LUX","0","TRUE","0"},
                {"RESTAURANT","13","0","LUX","0","TRUE","54","something"}
        };
        return new Object[][] {
                {false, array[0]},
                {false, array[1]},
                {false, array[2]},
                {false, array[3]}
        };
    }

    @Test(groups = {"Validator"}, description = "Test of the regex for string.",
          dataProvider = "PositiveDateProviderForValidateStr")
    public void testValidateStrPositive(boolean expectFlag, String str) {
        boolean actualFlag = validator.validateStr(str);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the regex for string.",
          dataProvider = "NegativeDateProviderForValidateStr")
    public void testValidateStrNegative(boolean expectFlag, String str) {
        boolean actualFlag = validator.validateStr(str);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the regex for int.",
          dataProvider = "PositiveDateProviderForValidateInt")
    public void testValidateIntPositive(boolean expectFlag, String str) {
        boolean actualFlag = validator.validateInt(str);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the regex for int.",
          dataProvider = "NegativeDateProviderForValidateInt")
    public void testValidateIntNegative(boolean expectFlag, String str) {
        boolean actualFlag = validator.validateInt(str);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the regex for double.",
          dataProvider = "PositiveDateProviderForValidateDouble")
    public void testValidateDoublePositive(boolean expectFlag, String str) {
        boolean actualFlag = validator.validateDouble(str);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the regex for double.",
          dataProvider = "NegativeDateProviderForValidateDouble")
    public void testValidateDoubleNegative(boolean expectFlag, String str) {
        boolean actualFlag = validator.validateDouble(str);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the regex for boolean.",
          dataProvider = "PositiveDateProviderForValidateBoolean")
    public void testValidateBooleanPositive(boolean expectFlag, String str) {
        boolean actualFlag = validator.validateBoolean(str);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the regex for boolean.",
          dataProvider = "NegativeDateProviderForValidateBoolean")
    public void testValidateBooleanNegative(boolean expectFlag, String str) {
        boolean actualFlag = validator.validateBoolean(str);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the validator"
            + " for passenger carriage.",
          dataProvider = "PositiveDateProviderForPassengerCarriage")
    public void testValidatePassengerPositive(boolean expectFlag, String[] array) {
        boolean actualFlag = validator.validatePassengerCarriage(array);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the validator"
            + " for passenger carriage.",
          dataProvider = "NegativeDateProviderForPassengerCarriage")
    public void testValidatePassengerNegative(boolean expectFlag, String[] array) {
        boolean actualFlag = validator.validatePassengerCarriage(array);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the validator"
            + " for compartment carriage.",
          dataProvider = "PositiveDateProviderForCompartmentCarriage")
    public void testValidateCompartmentPositive(boolean expectFlag, String[] array) {
        boolean actualFlag = validator.validateCompartment(array);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the validator"
            + " for compartment carriage.",
          dataProvider = "NegativeDateProviderForCompartmentCarriage")
    public void testValidateCompartmentNegative(boolean expectFlag, String[] array) {
        boolean actualFlag = validator.validateCompartment(array);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the validator"
            + " for econom-class carriage.",
          dataProvider = "PositiveDateProviderForEconomClassCarriage")
    public void testValidateEconomClassPositive(boolean expectFlag, String[] array) {
        boolean actualFlag = validator.validateEconomClass(array);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the validator"
            + " for econom-class carriage.",
          dataProvider = "NegativeDateProviderForEconomClassCarriage")
    public void testValidateEconomClassNegative(boolean expectFlag, String[] array) {
        boolean actualFlag = validator.validateEconomClass(array);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the validator"
            + " for seating carriage.",
          dataProvider = "PositiveDateProviderForSeatingCarriage")
    public void testValidateSeatingPositive(boolean expectFlag, String[] array) {
        boolean actualFlag = validator.validateSeating(array);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the validator"
            + " for seating carriage.",
          dataProvider = "NegativeDateProviderForSeatingCarriage")
    public void testValidateSeatingNegative(boolean expectFlag, String[] array) {
        boolean actualFlag = validator.validateSeating(array);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the validator"
            + " for restaurant carriage.",
          dataProvider = "PositiveDateProviderForRestaurantCarriage")
    public void testValidateRestaurantPositive(boolean expectFlag, String[] array) {
        boolean actualFlag = validator.validateRestaurant(array);
        assertEquals(actualFlag, expectFlag);
    }

    @Test(groups = {"Validator"}, description = "Test of the validator"
            + " for restaurant carriage.",
          dataProvider = "NegativeDateProviderForRestaurantCarriage")
    public void testValidateRestaurantNegative(boolean expectFlag, String[] array)  {
        boolean actualFlag = validator.validateRestaurant(array);
        assertEquals(actualFlag, expectFlag);
    }
}