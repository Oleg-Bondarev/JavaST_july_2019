package by.training.task11_reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**Main class.*/
public final class Runner {
    /**Def. constr.*/
    private Runner() { }
    /**Main meth.
     * @param args -program argums.*/
    public static void main(final String[] args) {
        CommonResource commonResource = new CommonResource();
        ReentrantLock locker = new ReentrantLock();
        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new CountThread(commonResource, locker));
            t.setName("Thread " + i);
            t.start();
        }
    }
}
