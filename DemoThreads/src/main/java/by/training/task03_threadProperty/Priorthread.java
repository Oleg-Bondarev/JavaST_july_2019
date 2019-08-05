package by.training.task03_threadProperty;

import java.util.concurrent.TimeUnit;

/**Run class.*/
public class Priorthread extends Thread {
    /**Constr.
     * @param name -name th.*/
    public  Priorthread(final String name) {
        super(name);
    }
    /**Run method.*/
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(getName() + " " + i);
            yield();
            if ((i == 5) && getName().equals("Max")) {
                interrupt();
                if (isInterrupted()) {
                    return;
                }
                System.out.println("State= " + getState());
            }
            try {
                TimeUnit.MICROSECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
