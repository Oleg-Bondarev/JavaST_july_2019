package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.validator.MatrixValidator;
import by.training.multithreading_matrix.service.ServiceException;

/**represents types of matrix multiplication.*/
public class MultiplicateMatrix {
    /**Matrix validator.*/
    private MatrixValidator matrixValidator = new MatrixValidator();
    /**Multiplication in one thread.
     * @param matrixA -first matrix.
     * @param matrixB -second matrix.
     * @exception ServiceException -if we have incorrect dimension.*/
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
        return resultMatrix;
    }

    /***/
    public Matrix multiplicationByMultiThread(final Matrix matrA,
                                              final Matrix matrB)
                                            throws ServiceException{
        return null;
    }
}
