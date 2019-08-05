package by.training.task15_resourcePool;

import java.util.LinkedList;

/**Main class.*/
public final class Runner {
    /**Def. constr.*/
    private Runner() { }
    /**Main meth.
     * @param args -progr args.*/
    public static void main(final String[] args) {
        LinkedList<AudioChannel> list = new LinkedList<AudioChannel>() {
            {
                this.add(new AudioChannel(771));
                this.add(new AudioChannel(883));
                this.add(new AudioChannel(550));
                this.add(new AudioChannel(337));
                this.add(new AudioChannel(442));
            }
        };
        ChannelPool<AudioChannel> pool = new ChannelPool<>(list);
        for (int i = 0; i < 20; i++) {
            new Client(pool).start();
        }
    }
}
