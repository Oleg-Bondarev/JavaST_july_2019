package by.training.task13_commonResourceSemaphore;

import java.util.concurrent.Semaphore;

/**Main class.*/
public final class Runner {
    /**Def. constr.*/
    private Runner() { }
    /**Main meth.
     * @param args -progr. args*/
    public static void main(final String[] args) {
        Semaphore sem = new Semaphore(1);
        CommonResource res = new CommonResource();
        Thread th1 = new Thread(new CountThread(res, sem, "CountThread 1"));
        Thread th2 = new Thread(new CountThread(res, sem, "CountThread 2"));
        Thread th3 = new Thread(new CountThread(res, sem, "CountThread 3"));

        th1.start();
        th2.start();
        th3.start();
    }

}
