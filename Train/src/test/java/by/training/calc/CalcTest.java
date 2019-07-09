package by.training.calc;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CalcTest {

    Calc calc = new Calc();

    @DataProvider(name = "PositiveDateProvider")
    public Object[][] createPositiveForSqrt() {
        return new Object[][] {
                {4.0, 2.0},
                {0.36, 0.6},
                {100.0, 10.0},
                {0.0, 0.0},
                {25, 5.0},
                {Integer.MAX_VALUE, 46340.950001051984}
        };
    }

    @DataProvider(name = "NegativeDateProvider")
    public Object[] createNegativeForSqrt() {
        return new Object[] {
                -4.0,
                -0.36,
                -100.0,
                -25
        };
    }

    @Test(description = "Positive scenario of the sqrt",
          dataProvider = "PositiveDateProvider")
    public void testSqrt(double a, double c) throws Exception{
        double act = calc.sqrt(a);
        assertEquals(act, c);
    }

   /* @Test(description = "Negative scenario of the sqrt",
          dataProvider = "NegativeDateProvider")
    public void testSqrtNegative(double a) {
        assertThrows();
    }*/
}