package by.training.multithreading_matrix.dao;

import by.training.multithreading_matrix.entity.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**Realisation of the matrix DAO.*/
public class MatrixRepository implements MatrixDAO {
    /**Instance.*/
    private static final MatrixRepository INSTANCE = new MatrixRepository();
    /**List f the matrix.*/
    private List<Matrix> matrixList = new ArrayList<>();
    /**Default constructor.*/
    private MatrixRepository() { }
    /**Constructor.
     * @param matrixListNew -new list of matrix.
     * */
    private MatrixRepository(final Optional<List<Matrix>> matrixListNew) {
        if (matrixListNew.isPresent()) {
            this.matrixList = matrixListNew.get();
        }
    }
    /**@return instance.*/
    public static MatrixRepository getInstance() {
        return INSTANCE;
    }
    /**@return copy of matrix list*/
    public List<Matrix> getMatrixList() {
        //TODO clone collection
        return matrixList;
    }
    /**@param newMatrix -new matrix.*/
    @Override
    public void addMatrix(Optional<Matrix> newMatrix) {
        if (newMatrix.isPresent()) {
            matrixList.add(newMatrix.get());
        }
    }

    @Override
    public void removeMatrix(Optional<Matrix> matrix) {
        if (matrix.isPresent()) {
            matrixList.remove(matrix.get());
        }
    }
}
