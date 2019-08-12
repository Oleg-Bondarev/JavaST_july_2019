package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;

import java.util.concurrent.Callable;

/**
 * Realisation of the callable interface.
 * */
public class MultiThreadsElementCalculator implements Callable<Integer> {
    /**
     * Current row.
     * */
    private int row;
    /**
     * Current column.
     * */
    private int column;
    /**
     * First matrix.
     * */
    private Matrix matrixA;
    /**
     * second matrix.
     * */
    private Matrix matrixB;

    /**
     * Constructor with parameters.
     * @param newRow     -new matrix row.
     * @param newColumn  -new matrix column.
     * @param newMatrixA -new matrix A.
     * @param newMatrixB -new matrix B.
     * */
    MultiThreadsElementCalculator(final int newRow, final int newColumn,
                                         final Matrix newMatrixA,
                                         final Matrix newMatrixB) {
        this.row = newRow;
        this.column = newColumn;
        this.matrixA = newMatrixA;
        this.matrixB = newMatrixB;
    }

    /**
     * @return -return value to the calling thread.
     * */
    @Override
    public Integer call() {
        int result = 0;
        int temp = matrixB.getVerticalSize();

        for (int k = 0; k < temp; k++) {
            result += matrixA.getElement(row, k)
                    * matrixB.getElement(k, column);
        }
        return result;
    }
}
