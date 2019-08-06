package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.validator.MatrixValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**represents types of matrix multiplication.*/
public class MultiplicateMatrix {
    /**Logger.*/
    private final Logger logger =
            LogManager.getLogger(MultiplicateMatrix.class);
    /**Matrix validator.*/
    private MatrixValidator matrixValidator = new MatrixValidator();
    /**Multiplication in one thread.
     * @param matrixA -first matrix.
     * @param matrixB -second matrix.
     * @exception ServiceException -if we have incorrect dimension.
     * @return matrix.*/
    public Matrix multiplicationByOneThread(final Matrix matrixA,
                                            final Matrix matrixB)
                                            throws ServiceException {
        if (!matrixValidator.checkDimension(matrixA, matrixB)) {
            throw new ServiceException("Incorrect matrix. Count rows in the "
                + "matrix A must be equal count of columns in the matrix B.");
        }

        int vert = matrixA.getVerticalSize();
        int horiz = matrixB.getHorizontalSize();
        int temp = matrixA.getHorizontalSize();
        Matrix resultMatrix = new Matrix(vert, horiz);
        int value;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < vert; i++) {
            for (int j = 0; j < horiz; j++) {
                value = 0;
                for (int k = 0; k < temp; k++) {
                    value += matrixA.getElement(i, k)
                            * matrixB.getElement(k, j);
                }
                resultMatrix.setElement(i, j, value);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("One thread time: " + (endTime - startTime));

        return resultMatrix;
    }

    /**Multi thread multiplication.
     * @param matrixA -first matrix.
     * @param matrixB -second matrix.
     * @param countThreads -count of threads for work.
     * @return result of the multiplication.
     * @exception ServiceException if dimensions are incorrect.*/
    public Matrix multiplicationByMultiThread(final Matrix matrixA,
                                              final Matrix matrixB,
                                              final int countThreads)
                                            throws ServiceException {
        if (!matrixValidator.checkDimension(matrixA, matrixB)) {
            throw new ServiceException("Incorrect matrix. Count rows in the "
                + "matrix A must be equal count of columns in the matrix B.");
        }

        ExecutorService executor = Executors.newFixedThreadPool(countThreads);
        List<Future<Integer>> resultCalculationList = new ArrayList<>();
        int countRows = matrixA.getVerticalSize();
        int countColumns = matrixB.getHorizontalSize();
        int elementPosition;
        int elementToInsert;
        Matrix matrixC = new Matrix(countRows, countColumns);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countColumns; j++) {
                resultCalculationList.add(executor
                        .submit(new MultithreadsElementCalculator(i, j,
                                matrixA, matrixB)));
            }
        }

        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countColumns; j++) {
                elementPosition = i * countColumns + j;
                try {
                    elementToInsert =
                            resultCalculationList.get(elementPosition).get();
                    matrixC.setElement(i, j, elementToInsert);
                }  catch (InterruptedException e) {
                    logger.log(Level.WARN, "Interrupted!");
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    throw new ServiceException("Execution exception."
                            + e.getMessage());
                }
            }
        }
        executor.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println("Multithreading time: " + (endTime - startTime));
        return matrixC;
    }
}
