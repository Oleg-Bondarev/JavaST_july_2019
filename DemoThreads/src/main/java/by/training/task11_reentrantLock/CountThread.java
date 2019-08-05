package by.training.task11_reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CountThread implements Runnable {
    /**Common res.*/
    private CommonResource res;
    /**Locker.*/
    private ReentrantLock locker;
    /**Constr.
     * @param newRes -resource.
     * @param newLock -locker.*/
    CountThread(final CommonResource newRes, final ReentrantLock newLock) {
        this.res = newRes;
        locker = newLock;
    }
    /**Runner meth.
     * @exception InterruptedException -sleep fail.*/
    public void run() {
        locker.lock();
        try {
            res.setX(1);
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n",
                        Thread.currentThread().getName(), res.getX());
                res.setX(res.getX() + 1);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock(); // снимаем блокировку
        }
    }

}
