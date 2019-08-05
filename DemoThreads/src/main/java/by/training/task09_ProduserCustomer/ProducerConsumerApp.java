package by.training.task09_ProduserCustomer;

/**Main class.*/
public final class ProducerConsumerApp {
    /**constr.*/
    private ProducerConsumerApp() { }
    /**@param args -progr args.*/
    public static void main(final String[] args) {
        Store store = new Store();
        new Producer(store).start();
        new Consumer(store).start();
    }
}
