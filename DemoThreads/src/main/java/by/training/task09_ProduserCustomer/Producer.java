package by.training.task09_ProduserCustomer;

import java.util.concurrent.TimeUnit;

/**producer thread.*/
public class Producer extends Thread {
    /**
     * Store obj.
     */
    Store store;
    /**
     * count items to add.
     */
    int product = 5;

    /**
     * constr.
     *
     * @param newStore -stor class obj.
     */
    Producer(final Store newStore) {
        this.store = newStore;
    }

    /**
     * Run method.
     */
    public void run() {
        try {
            while (product > 0) {
                product = product - store.put();
                System.out.println("производителю осталось произвести "
                        + product + " товар(ов)");
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("поток производителя прерван");
        }
    }
}


