package by.training.task17_auction;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**Thread that use barrier.*/
public class Bid extends Thread {
    /**ID.*/
    private Integer bidId;
    /**price of the lot.*/
    private int price;
    /**Barrier.*/
    private CyclicBarrier barrier;
    /**@param idNew of the bid.
     * @param barrierNew -barrier.
     * @param priceNew of the lot.*/
    public Bid(final int idNew, final int priceNew, final CyclicBarrier barrierNew) {
        this.bidId = idNew;
        this.price = priceNew;
        this.barrier = barrierNew;
    }
    /**@return bid ID.*/
    public Integer getBidId() {
        return bidId;
    }
    /**@return price of the lot.*/
    public int getPrice() {
        return price;
    }
    /**Runner meth.*/
    @Override
    public void run() {
        try {
            System.out.println("Client " + this.bidId + " specifies a price.");
            Thread.sleep(new Random().nextInt(3000)); // время на раздумье
            // определение уровня повышения цены
            int delta = new Random().nextInt(50);
            price += delta;
            System.out.println("Bid " + this.bidId + " : " + price);
            this.barrier.await(); // остановка у барьера
            System.out.println("Continue to work..."); // проверить кто выиграл
            // и оплатить в случае победы ставки
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
