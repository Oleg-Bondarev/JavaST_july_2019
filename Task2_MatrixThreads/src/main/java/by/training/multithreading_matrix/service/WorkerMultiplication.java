package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.entity.MatrixException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represent runnable realisation.
 * */
public class WorkerMultiplication implements Runnable {
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * First matrix.
     * */
    private Matrix matrixA;
    /**
     * Second matrix.
     * */
    private Matrix matrixB;
    /**
     * Result matrix.
     * */
    private Matrix matrixC;
    /**
     * Number of row for thread.
     * */
    private int rowNumber;
    /**
     * Constructor.
     * @param rowNum -number of row for thread.
     * @param mA     -first matrix.
     * @param mB     -second matrix.
     * @param mC     -result matrix.
     */
    WorkerMultiplication(final int rowNum, final Matrix mA,
                                final Matrix mB,
                                final Matrix mC) {
        this.rowNumber = rowNum;
        this.matrixA = mA;
        this.matrixB = mB;
        this.matrixC = mC;
    }
    /**Run method.*/
    @Override
    public void run() {
        int temp = matrixB.getVerticalSize();
        int columns = matrixA.getVerticalSize();
        for (int i = 0; i <= temp; i++) {
            for (int j = 0; j < columns; j++) {
                try {
                    matrixC.setElement(i, j, calculateElement(i, j));
                } catch (MatrixException e) {
                    LOGGER.log(Level.ERROR, e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    /**
     * @param row -start row for each thread.
     * @param col -stop row for each thread.
     * @return element of result matrix.
     */
    private int calculateElement(final int row, final int col) {
        int result = 0;
        for (int i = 0; i < matrixB.getVerticalSize(); ++i) {
            result += matrixA.getElement(row, i)
                    * matrixB.getElement(i, col);
        }
        return result;
    }
}
