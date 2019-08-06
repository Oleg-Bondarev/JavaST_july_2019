package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;

import java.util.List;

/**Represent matrix service.*/
public interface MatrixService {
    /**matrix generation.
     * @param matr      -matrix.
     * @param start     -start of the generation.
     * @param end stop genetayion.
     * @return matrix*/
    Matrix generateMatrix(Matrix matr, int start, int end);
    /**matrix multiplication.
     * @param matrixA -first matr.
     * @param matrixB -second matr.
     * @param countThreads -count of using threads.
     * @throws ServiceException if have incorrect information.
     * @return matrix.*/
    Matrix multiplicateMatrix(Matrix matrixA, Matrix matrixB, int countThreads)
            throws ServiceException;
    /**Creating matrix from text data.
     * @param list -list of input information.
     * @return matrix.
     * @throws ServiceException if have incorrect information.*/
    Matrix createMatrix(List<String> list) throws ServiceException;
}
