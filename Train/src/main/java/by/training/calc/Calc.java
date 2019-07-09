package by.training.calc;

public class Calc {
    public double sqrt(double a) throws Exception{
        if (a >= 0) {
            return Math.sqrt(a);
        } else {
            throw new Exception();
        }
    }
}
