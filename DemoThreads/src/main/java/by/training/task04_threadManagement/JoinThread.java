package by.training.task04_threadManagement;

import java.util.concurrent.TimeUnit;

/**Class with run meth.*/
public class JoinThread extends Thread {
    /**Constr.
     * @param name -name th.
     */
    public JoinThread(final String name) {
        super(name);
    }
    /**Run meth.*/
    public void run() {
        String nameTh = getName();
        long timeOut = 0;
        System.out.println("Thread start: " + nameTh);
        try {
            switch (nameTh) {
                case "First":
                    timeOut = 5000;
                    break;
                case "Second":
                    timeOut = 1000;
                    break;
                default:
                    break;
            }
            TimeUnit.MILLISECONDS.sleep(timeOut);
            System.out.println("Thread end: " + nameTh);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
