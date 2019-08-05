package by.training.task09_ProduserCustomer;

import java.util.concurrent.TimeUnit;

class Consumer extends Thread {
    /**
     * Store obj.
     */
    Store store;
    /**
     * count items on store.
     */
    int product = 5;

    /**
     * Constr.
     *
     * @param newStore -object of class Store.
     */
    Consumer(final Store newStore) {
        this.store = newStore;
    }

    /**
     * Run method.
     *
     * @throws InterruptedException -thread sleep exc.
     */
    public void run() {
        try {
            while (product < product)
                product = product + store.get();
            System.out.println("Потребитель купил " + product
                    + " товар(ов)");
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
