package by.training.task12_ProducerConsumerReentrantLock;

/**Item producer class.*/
public class Producer implements Runnable {
    /**Store obj.*/
    private Store store;
    /**Constr.
     * @param newStore -store obj.*/
    Producer(final Store newStore) {
        this.store = newStore;
    }
    /**Runner meth.*/
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }
}
