package by.training.task10_produserConsumer_wait_notify;

/**Class of item store.*/
public class Store {
    /**Count of items in the store.*/
    private int product = 0;
    /**Put item in the store.
     * @exception InterruptedException -wait thread fail.*/
    public synchronized void put() {
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        product++;
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
    /**Get item from the store.
     * @exception InterruptedException -wait thread fail.*/
    public synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        product--;
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
}
