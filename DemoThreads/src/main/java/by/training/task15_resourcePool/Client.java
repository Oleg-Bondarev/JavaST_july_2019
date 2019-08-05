package by.training.task15_resourcePool;

/**Client class.*/
public class Client extends Thread {
    /**reading of the channel.*/
    private boolean reading = false;
    /**pool.*/
    private ChannelPool<AudioChannel> pool;
    /**@param poolNew -new pool.*/
    public Client(final ChannelPool<AudioChannel> poolNew) {
        this.pool = poolNew;
    }
    /**runner meth.*/
    public void run() {
        AudioChannel channel = null;
        try {
            channel = pool.getResource(100); // изменить на 100
            reading = true;
            System.out.println("Channel Client #" + this.getId()
                    + " took channel #" + channel.getChannelId());
            channel.using();
        } catch (ResourceException e) {
            System.out.println("Client #" + this.getId() + " lost ->"
                    + e.getMessage());
        } finally {
            if (channel != null) {
                reading = false;
                System.out.println("Channel Client #" + this.getId() + " : "
                        + channel.getChannelId() + " channel released");
                pool.returnResource(channel);
            }
        }
    }
    /**check is channel ready.
     * @return boolean value.*/
    public boolean isReading() {
        return reading;
    }
}
