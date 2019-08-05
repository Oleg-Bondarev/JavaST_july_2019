package by.training.task17_auction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CyclicBarrier;

/**Class auction.*/
public class Auction {
    /**bids of clients.*/
    private ArrayList<Bid> bids;
    /**barrier.*/
    private CyclicBarrier barrier;
    /**count threads.*/
    public final int BIDS_NUMBER = 5;
    /***/
    public Auction() {
        this.bids = new ArrayList<Bid>();
        this.barrier = new CyclicBarrier(this.BIDS_NUMBER, new Runnable() {
            public void run() {
                Bid winner = Auction.this.defineWinner();
                System.out.println("Bid #" + winner.getBidId() + ", price:" + winner.getPrice() + " win!");
            }
        });
    }
    /**@return  barrier.*/
    public CyclicBarrier getBarrier() {
        return barrier;
    }
    /**@param e - new bid.*/
    public boolean add(Bid e) {
        return bids.add(e);
    }
    /**@return winner bid.*/
    public Bid defineWinner() {
        return Collections.max(bids, new Comparator<Bid>() {
            @Override
            public int compare(Bid ob1, Bid ob2) {
                return ob1.getPrice() - ob2.getPrice();
            }
        });
    }
}
