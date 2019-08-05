package by.training.task19_ExecutorServiceAndCallable;

import java.util.Random;
import java.util.concurrent.Callable;
/**Return result thread.*/
public class CalcCallable implements Callable<Number> {
    /**@return result.*/
    @Override
    public Number call() throws Exception {
        Number result = new Random().nextGaussian();
        return result;

    }
}
