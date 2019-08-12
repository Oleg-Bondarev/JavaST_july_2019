package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.entity.MatrixException;
import by.training.multithreading_matrix.service.interfaces.MatrixService;
import by.training.multithreading_matrix.validator.MatrixValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**Realisation of the Matrix service interface.*/
public class MatrixServiceImpl implements MatrixService {
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * @param matrix -matrix to fill random elements.
     * @throws ServiceException -have some problems.
     * */
    @Override
    public Matrix generateMatrix(final Matrix matrix, final int start,
                               final int end) throws ServiceException {
        GenerateMatrix generateMatrix = new GenerateMatrix();
        generateMatrix.fillRandomize(matrix, start, end);
        return matrix;
    }
    /**
     * @param matrixA -matrix.
     * @param matrixB -matrix.
     * @param threads -count threads.
     * @throws ServiceException if have incorrect information.
     * */
    @Override
    public Matrix multiplicateMatrix(final Matrix matrixA, final Matrix matrixB,
                                     final int threads)
            throws ServiceException {
        if (threads < 1) {
            throw new ServiceException("Incorrect count of threads: "
                    + threads);
        } else {
            MultiplicateMatrix multiplicateMatrix = new MultiplicateMatrix();
            if (threads == 1) {
                return multiplicateMatrix.multiplicationByOneThread(matrixA,
                        matrixB);
            } else {
                return multiplicateMatrix
                        .multiplicationByMultiThread(matrixA, matrixB, threads);
            }
        }
    }
    /**
     * @param matrixA -matrix.
     * @param matrixB -matrix.
     * @param threads -count threads.
     * @throws ServiceException if have incorrect information.
     * */
    @Override
    public Matrix multiplicationByMultiThreadSecondWay(final Matrix matrixA,
                                                       final Matrix matrixB,
                                                        final int threads)
                                                    throws ServiceException {
        if (threads < 1) {
            throw new ServiceException("Incorrect count of threads: "
                    + threads);
        } else {
            MultiplicateMatrix multiplicateMatrix = new MultiplicateMatrix();
            return multiplicateMatrix
                    .multiplicationByMultiThreadSecondWay(matrixA, matrixB,
                                                                    threads);
        }
    }
    /**
     * @param information -input information.
     * @return matrix.
     * @throws ServiceException if have incorrect information.
     * */
    @Override
    public Matrix createMatrix(final List<String> information)
            throws ServiceException {
        MatrixValidator validator = new MatrixValidator();
        if (!validator.validateMatrix(information)) {
            throw new ServiceException("Incorrect matrix parameters.");
        }

        final String regexForSplitDimension = "\\*";
        final String regexForSplitElements = "[ ]+";
        String temp = information.get(0).trim();
        String[] elements = temp.split(regexForSplitDimension);
        int rows = Integer.parseInt(elements[0]);
        int columns = Integer.parseInt(elements[1]);
        Matrix matrix = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            elements =
                    information.get(i + 1).trim().split(regexForSplitElements);
            for (int j = 0; j < columns; j++) {
                try {
                    matrix.setElement(i, j, Integer.parseInt(elements[j]));
                } catch (MatrixException e) {
                    throw new ServiceException(e.getMessage());
                }
            }
        }
        return matrix;
    }
    /**
     * @param matrix -input matrix.
     * @return modernized matrix.
     * @throws ServiceException -have somme problems.
     * */
    @Override
    public Matrix fillZeroOnDiagonal(final Matrix matrix)
            throws ServiceException {
        int dimensiomn = matrix.getHorizontalSize();
        for (int i = 0; i < dimensiomn; i++) {
            try {
                matrix.setElement(i, i, 0);
            } catch (MatrixException e) {
                throw new ServiceException(e.getMessage());
            }
        }
        return matrix;
    }
    /**
     * @param matrix input matrix.
     * @return       modernized matrix.
     * */
    @Override
    public Matrix transformDiagonalByThreads(final Matrix matrix) {
        ExecutorService executor = ThreadService.getInstance().getExecutor();
        List<Lock> locksList = new ArrayList<>();
        int countColumns = matrix.getHorizontalSize();
        for (int i = 0; i < countColumns; i++) {
            locksList.add(new ReentrantLock());
        }
        for (int i = 0; i < countColumns; i++) {
            executor.execute(new DiagonalCreator(matrix, locksList));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Diagonal transformation hasn't been "
                    + "finished in 1 minute.", e);
            Thread.currentThread().interrupt();
        }
        return matrix;
    }
}
