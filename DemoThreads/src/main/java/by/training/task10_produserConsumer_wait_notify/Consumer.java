package by.training.task10_produserConsumer_wait_notify;

public class Consumer extends Thread {
    /**Store obj.*/
    private Store store;
    /**Def constr.
     * @param newStore -store obj.*/
    Consumer(final Store newStore) {
        this.store = store;
    }
    /**Run meth.*/
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }

}
