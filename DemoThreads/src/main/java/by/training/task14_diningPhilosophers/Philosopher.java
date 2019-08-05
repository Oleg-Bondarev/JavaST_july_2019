package by.training.task14_diningPhilosophers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**Philosopher class with method run.*/
public class Philosopher extends Thread {
    /**Semaphore.*/
    private Semaphore semaphore;
    /**Count of dishes.*/
    private int dishes = 0;
    /**Philosopher ID.*/
    private int id;

    /**Constr.
     * @param sem -semaphore.
     * @param idNew -philosopher id.*/
    Philosopher(final Semaphore sem, final int idNew) {
        this.semaphore = sem;
        this.id = idNew;
    }
    /**runner meth.*/
    public void run() {
        try {
            while (dishes < 3) {
                semaphore.acquire();
                System.out.println("Философ " + id + " садится за стол");
                TimeUnit.MILLISECONDS.sleep(500);
                dishes++;
                System.out.println("Философ " + id + " выходит из-за стола");
                semaphore.release();
                // философ гуляет
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("у философа " + id + " проблемы со здоровьем");
        }
    }
}
