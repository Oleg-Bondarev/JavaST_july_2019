package by.training.multithreading_matrix.dao;

import by.training.multithreading_matrix.entity.Matrix;

import java.util.Optional;

/**Represent the matrix DAO.*/
public interface MatrixDAO {
    /** Add matrix to the list of matrix.
     * @param newMatrix - new carriage.
     * */
    void addMatrix(Optional<Matrix> newMatrix);
    /**
     * Remove matrix from the list of the matrix.
     * @param matrix - carriage that we want to remove.
     * */
    void removeMatrix(Optional<Matrix> matrix);
}
