package by.training.task12_ProducerConsumerReentrantLock;

/**Main class.*/
public final class ProducerConsumerApp {
    /**Def. constr.*/
    private ProducerConsumerApp() { }
    /**main meth.
     * @param args -progr. args.*/
    public static void main(final String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        Thread th1 = new Thread(producer);
        Thread th2 = new Thread(consumer);
        th1.start();
        th2.start();
    }
}
