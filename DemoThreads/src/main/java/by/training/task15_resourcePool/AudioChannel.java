package by.training.task15_resourcePool;

import java.util.concurrent.TimeUnit;

/**Type of the resource.*/
public class AudioChannel {
    /**Id of the channel.*/
    private int channelId;
    /**Constr.
     * @param id id of the channel.*/
    public AudioChannel(final int id) {
        super();
        this.channelId = id;
    }
    /**Getter.
     * @return channel ID.*/
    public int getChannelId() {
        return channelId;
    }
    /**Setter.
     * @param id -ID.*/
    public void setChannelId(final int id) {
        this.channelId = id;
    }
    /**Using f the channel.*/
    public void using() {
        try {
            TimeUnit.MILLISECONDS.sleep(new java.util.Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
