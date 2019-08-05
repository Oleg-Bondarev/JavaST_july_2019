package by.training.task17_auction;

import java.util.Random;
/**Main class.*/
public final class Runner {
    /**Def constr.*/
    private Runner() { }
    /**@param args -program args.*/
    public static void main(final String[ ] args) {
        Auction auction = new Auction();
        int startPrice = new Random().nextInt(100);
        for (int i = 0; i < auction.BIDS_NUMBER; i++) {
            Bid thread = new Bid(i, startPrice, auction.getBarrier());
            auction.add(thread);
            thread.start();
        }
    }
}
