package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.entity.MatrixException;
import by.training.multithreading_matrix.entity.MatrixThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Represent thread work in diagonal updating.
 * */
public class DiagonalCreator implements Runnable {
    /**
     * Matrix class object.
     * */
    private Matrix matrix;
    /**
     * List of rows lockers.
     * */
    private List<Lock> loksList;
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Constructor.
     * @param newMatrix -matrix for thread work.
     * @param listLock  -list of the locks.
     * */
    DiagonalCreator(final Matrix newMatrix, final List<Lock> listLock) {
        this.matrix = newMatrix;
        this.loksList = listLock;
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
                || !loksList.get(position).tryLock()) {
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
                Thread.currentThread().interrupt();
            }
            try {
                matrix.setElement(position, position,
                                                thread.getNumberForThread());
            } catch (MatrixException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
                Thread.currentThread().interrupt();
            }
            LOGGER.log(Level.INFO, "Thread: " + thread.getName() + " ->["
                    + thread.getNumberForThread() + "] update position ("
                    + position + "," + position + ")");
        }
        loksList.get(position).unlock();
    }
}
