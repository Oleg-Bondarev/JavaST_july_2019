package by.training.task13_commonResourceSemaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CountThread implements Runnable {
    /**Common res.*/
    private CommonResource res;
    /**Semaphore.*/
    private Semaphore sem;
    /**Thread name.*/
    private String name;
    /**@param resNew common res.
     * @param semNew semaphore.
     * @param nameNew thread name.*/
    CountThread(final CommonResource resNew, final Semaphore semNew,
                final String nameNew) {
        this.res = resNew;
        this.sem = semNew;
        this.name = nameNew;
    }
    /**Runner meth.*/
    public void run() {
        try {
            System.out.println(name + " ожидает разрешение");
            sem.acquire();
            res.setX(1);
            for (int i = 1; i < 5; i++) {
                System.out.println(this.name + ": " + res.getX());
                res.setX(res.getX() + 1);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(name + " освобождает разрешение");
        sem.release();
    }
}
