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
     * Start row.
     * */
    private int startRow;
    /**
     * Stop row.
     * */
    private int stopRow;
    /**
     * Constructor.
     * @param mA     -first matrix.
     * @param mB     -second matrix.
     * @param mC     -result matrix.
     * @param start  -start row.
     * @param stop    -end row.
     */
    WorkerMultiplication(final Matrix mA, final Matrix mB, final Matrix mC,
                         final int start, final int stop) {
        this.matrixA = mA;
        this.matrixB = mB;
        this.matrixC = mC;
        this.startRow = start;
        this.stopRow = stop;
    }
    /**Run method.*/
    @Override
    public void run() {
        int columns = matrixC.getHorizontalSize();
        for (int i = startRow; i <= stopRow; ++i) {
            for (int j = 0; j < columns; ++j) {
                try {
                    matrixC.setElement(i, j, calculateElement(i, j));
                } catch (MatrixException e) {
                    LOGGER.log(Level.ERROR, e.getMessage());
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
        int rows = matrixB.getVerticalSize();
        for (int i = 0; i < rows; ++i) {
            result += matrixA.getElement(row, i)
                    * matrixB.getElement(i, col);
        }
        return result;
    }
}
