package by.training.task05_threadToDisable;

import java.util.concurrent.TimeUnit;

/***/
public class ThreadToDisable implements Runnable {
    /**is thread active.*/
    private boolean isActive;
    /**disabled thread.*/
    void disable() {
        isActive = false;
    }
    ThreadToDisable() {
        isActive = true;
    }
    /**Run meth.*/
    @Override
    public void run() {
        System.out.printf("Thread %s start... \n", Thread.currentThread().getName());
        int counter = 1;
        while (isActive) {
            System.out.println("Cicle " + counter++);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Thread %s end... \n", Thread.currentThread().getName());
    }
}
