package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.validator.MatrixValidator;

import java.util.List;

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
     * @param threads -count threads.*/
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
     * @return matrix.*/
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
            elements = information.get(i).trim().split(regexForSplitElements);
            for (int j = 0; j < columns; j++) {
                matrix.setElement(i, j, Integer.parseInt(elements[j]));
            }
        }
        return matrix;
    }
}
