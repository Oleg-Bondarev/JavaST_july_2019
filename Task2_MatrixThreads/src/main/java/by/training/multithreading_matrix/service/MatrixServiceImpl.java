package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.service.interfaces.MatrixService;
import by.training.multithreading_matrix.validator.MatrixValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**Realisation of the Matrix service interface.*/
public class MatrixServiceImpl implements MatrixService {
    /**@param matr -matrix to */
    @Override
    public Matrix generateMatrix(final Matrix matr, final int start,
                               final int end) {
        GenerateMatrix generateMatrix = new GenerateMatrix();
        generateMatrix.fillRandomizer(matr, start, end);
        return matr;
    }
    /**@param matrixA -matrix.
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
    /**@param information -input information.
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
                matrix.setElement(i, j, Integer.parseInt(elements[j]));
            }
        }
        return matrix;
    }
    /**
     * @param matrix -input matrix.
     * @return modernized matrix.
     * */
    @Override
    public Matrix fillZeroOnDiagonal(final Matrix matrix) {
        int dimensiomn = matrix.getHorizontalSize();
        for (int i = 0; i < dimensiomn; i++) {
            matrix.setElement(i, i, 0);
        }
        return matrix;
    }
    /**
     * @param matrix input matrix.
     * @return       modernized matrix.
     * @throws        ServiceException if have interrupt exception.
     * */
    @Override
    public Matrix transformDiagonalByThreads(final Matrix matrix)
            throws ServiceException {
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
            throw new Servi ceException("Diagonal transformation hasn't been "
                    + "finished in 1 minute.", e);
        }
        return matrix;
    }
}
