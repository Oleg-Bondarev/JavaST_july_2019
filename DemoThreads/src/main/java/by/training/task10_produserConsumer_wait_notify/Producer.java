package by.training.task10_produserConsumer_wait_notify;

/**Produser class.*/
public class Producer extends Thread {
    /**Store obj.*/
    private Store store;
    /**Def constr.
     * @param newStore -store obj.*/
    Producer(final Store newStore) {
        this.store = newStore;
    }
    /**Run method.*/
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }
}
