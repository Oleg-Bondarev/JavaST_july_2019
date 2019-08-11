package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.entity.MatrixThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class WorkWithSemaphore implements Runnable {
    /**
     * Number for thread.
     * */
    private int number;
    /**
     * Matrix class object.
     * */
    private Matrix matrix;
    /**
     * Semaphore.
     * */
    private Semaphore semaphore;
    /**
     * Logger.
     * */
    private static final Logger LOGGER =
            LogManager.getLogger(WorkWithSemaphore.class);
    /**
     * Constructor.
     * */
    public WorkWithSemaphore() { }
    /**
     * Constructor.
     * @param sem -semaphore.
     * @param num -number for thread.
     * */
    public WorkWithSemaphore(final Semaphore sem, final int num) {
        this.semaphore = sem;
        this.number = num;
        new Thread(this).start();
    }
    /**
     * Setter.
     * @param matr -matrix.
     */
    public void setMatrix(final Matrix matr) {
        this.matrix = matr;
    }
    /**
     * Run method.
     * */
    @Override
    public void run() {
        final int sleepTime = 500;
        int position = 0;
        int dimension = matrix.getHorizontalSize();
        while (((position < dimension)
                && (matrix.getElement(position, position) != 0))
                || !semaphore.tryAcquire()) {
            position++;
        }
        Thread currentThread = Thread.currentThread();
        MatrixThread thread;
        if (currentThread instanceof MatrixThread) {
            thread = (MatrixThread) currentThread;
            try {
                TimeUnit.MICROSECONDS.sleep(new Random().nextInt(sleepTime));
            } catch (InterruptedException e) {
                LOGGER.log(Level.ERROR, "Error during thread sleeping.", e);
            }
            matrix.setElement(position, position, thread.getNumberForThread());
            System.out.printf("Thread: %s ->[%d] update position (%d, %d)\n",
                    thread.getName(),  thread.getNumberForThread(), position,
                    position);
        }
        semaphore.release();
    }
    /**
     * Getter.
     * @return number for thread work.
     * */
    public int getNumberForThread() {
        return number;
    }
}
