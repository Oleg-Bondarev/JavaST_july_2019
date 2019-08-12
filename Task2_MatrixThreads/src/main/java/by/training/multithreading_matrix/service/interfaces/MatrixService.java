package by.training.multithreading_matrix.service.interfaces;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.service.ServiceException;

import java.util.List;

/**Represent matrix service.*/
public interface MatrixService {
    /**
     * matrix generation.
     * @param matr      -matrix.
     * @param start     -start of the generation.
     * @param end stop genetayion.
     * @return matrix
     * @throws ServiceException -if have problems.
     * */
    Matrix generateMatrix(Matrix matr, int start, int end)
            throws ServiceException;
    /**
     * matrix multiplication.
     * @param matrixA -first matr.
     * @param matrixB -second matr.
     * @param countThreads -count of using threads.
     * @throws ServiceException if have incorrect information.
     * @return matrix.
     * */
    Matrix multiplicationByMultiThreadSecondWay(Matrix matrixA, Matrix matrixB,
                                                int countThreads)
                                                throws ServiceException;
    /**
     * matrix multiplication.
     * @param matrixA -first matr.
     * @param matrixB -second matr.
     * @param countThreads -count of using threads.
     * @throws ServiceException if have incorrect information.
     * @return matrix.
     * */
    Matrix multiplicateMatrix(Matrix matrixA, Matrix matrixB, int countThreads)
            throws ServiceException;
    /**
     * Creating matrix from text data.
     * @param list -list of input information.
     * @return matrix.
     * @throws ServiceException if have incorrect information.
     * */
    Matrix createMatrix(List<String> list) throws ServiceException;
    /**
     * Filling zero on diagonal.
     * @param matrix -input matrix.
     * @return modernized matrix.
     * @throws ServiceException if have incorrect information.
     * */
    Matrix fillZeroOnDiagonal(Matrix matrix) throws ServiceException;
    /**
     * Transform matrix diagonal by threads.
     * @param matrix -input matrix.
     * @return modernized matrix.
     * */
    Matrix transformDiagonalByThreads(Matrix matrix);
}
