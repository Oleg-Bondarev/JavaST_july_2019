package by.training.task14_diningPhilosophers;

import java.util.concurrent.Semaphore;

/**Main class.*/
public final class PhilosophersApp {
    /**Def. constr.*/
    private PhilosophersApp() { }
    /**Main meth.
     * @param args progr. argums.*/
    public static void main(final String[] args) {
        final int countPhil = 6;
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i < countPhil; i++) {
            new Philosopher(semaphore, i).start();
        }
    }
}
