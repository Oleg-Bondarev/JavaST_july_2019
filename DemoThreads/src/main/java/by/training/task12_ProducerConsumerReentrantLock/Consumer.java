package by.training.task12_ProducerConsumerReentrantLock;

/**Item consumer class.*/
class Consumer implements Runnable {
    /**Store obj.*/
    private Store store;
    /**Constr.
     * @param newStore -store obj.*/
    Consumer(final Store newStore) {
        this.store = newStore;
    }
    /**Runner meth.*/
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}

