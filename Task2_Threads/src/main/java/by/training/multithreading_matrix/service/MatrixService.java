package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;

import java.util.List;

/**Represent matrix service.*/
public interface MatrixService {
    /**matrix generation.*/
    Matrix generateMatrix(Matrix matr, int start, int end);
    /**matrix multiplication.*/
    Matrix multiplicateMatrix(Matrix A, Matrix B, int countThreads) throws ServiceException;
    /**Get all matrix*/
    List<Matrix> allMatrix();
    /**Creating matrix from text data.*/
    Matrix createMatrix(List<String> list) throws ServiceException;
}
