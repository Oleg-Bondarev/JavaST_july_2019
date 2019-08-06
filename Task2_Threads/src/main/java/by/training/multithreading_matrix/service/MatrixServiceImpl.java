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

    @Override
    public Matrix multiplicateMatrix(final Matrix A, final Matrix B,
                                     final int threads)
            throws ServiceException {
        if (threads < 1) {
            throw new ServiceException("Incorrect count of threads: "
                    + threads);
        } else {
            MultiplicateMatrix multiplicateMatrix = new MultiplicateMatrix();
            if (threads == 1) {
                return multiplicateMatrix.multiplicationByOneThread(A, B);
            } else {
                return multiplicateMatrix.multiplicationByMultiThread(A, B);
            }
        }
    }

    @Override
    public List<Matrix> allMatrix() {
        /*List<Matrix> newList = matrixRepository.getMatrixList();
        return newList;*/
        return null; //TODO
    }

    @Override
    public Matrix createMatrix(final List<String> information)
            throws ServiceException {
        MatrixValidator validator = new MatrixValidator();
        if (!validator.validateMatrix(information)) {
            throw new ServiceException("Incorrect matrix parameters.");
        }

        final String regexForSplitDimension = "\\*";
        final String regexForSolitElements = "[ ]+";
        String temp = information.get(0).trim();
        String[] elements = temp.split(regexForSplitDimension);
        int rows = Integer.parseInt(elements[0]);
        int columns = Integer.parseInt(elements[1]);
        Matrix matrix = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            elements = information.get(i).trim().split(regexForSolitElements);
            for (int j = 0; j < columns; j++) {
                matrix.setElement(i, j, Integer.parseInt(elements[j]));
            }
        }
        return matrix;
    }
}
