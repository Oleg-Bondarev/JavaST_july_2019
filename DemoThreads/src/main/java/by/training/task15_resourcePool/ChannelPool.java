package by.training.task15_resourcePool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**Pool of the channels.
 * @param <T> type of the channel in poool.*/
public class ChannelPool<T> {
    /**Max poool size.*/
    private static final int POOL_SIZE = 5;
    /**Semaphore.*/
    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
    /**Queue of free resources.*/
    private final Queue<T> resources = new LinkedList<T>();
    /**Constr.
     * @param source -new recourse.*/
    public ChannelPool(final Queue<T> source) {
        resources.addAll(source);
    }
    /**Getter.
     * @param maxWaitMillis -milliseconds.
     * @return type of the resource.
     * @exception ResourceException -exception with resource.
     * */
    public T getResource(final long maxWaitMillis) throws ResourceException {
        try {
            if (semaphore.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)) {
                T res = resources.poll();
                return res;
            }
        } catch (InterruptedException e) {
            throw new ResourceException(e);
        }
        throw new ResourceException(":превышено время ожидания");
    }
    /**Return resource to the pool.
     * @param res resource.*/
    public void returnResource(final T res) {
        resources.add(res);
        semaphore.release();
    }
}
