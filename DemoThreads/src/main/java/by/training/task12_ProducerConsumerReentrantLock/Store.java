package by.training.task12_ProducerConsumerReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**Class of the item storage.*/
public class Store {
    /**count of the product.*/
    private int product = 0;
    /**Locker.*/
    private ReentrantLock locker;
    /**Condition.*/
    private Condition condition;

    /**Def. constr.*/
    Store() {
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }
    /**Get item.*/
    public void get() {
        locker.lock();
        try {
            while (product < 1) {
                condition.await();
            }
            product--;
            System.out.println("Покупатель купил 1 товар");
            System.out.println("Товаров на складе: " + product);
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }
    /**Put item.*/
    public void put() {
        locker.lock();
        try {
            while (product >= 3) {
                condition.await();
            }
            product++;
            System.out.println("Производитель добавил 1 товар");
            System.out.println("Товаров на складе: " + product);
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }
}
