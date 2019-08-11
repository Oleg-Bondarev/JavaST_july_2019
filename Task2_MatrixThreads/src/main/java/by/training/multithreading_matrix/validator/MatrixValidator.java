package by.training.multithreading_matrix.validator;

import by.training.multithreading_matrix.entity.Matrix;

import java.util.List;
import java.util.Optional;

/**
 * Validate parameters of the matrix.
 * */
public class MatrixValidator {
    /**
     * @param list -list with dimension and elements of the matrix.
     * @return boolean value.
     * */
    public boolean validateMatrix(final List<String> list) {
        int n;
        int m;
        String[] elements;
        final String regexForDigit = "\\d+";
        final String regex = "\\d+(\\*)\\d+";
        final String regexForDimension = "\\*";
        if (!list.get(0).matches(regex)) {
            return false;
        }
        String[] dimensions = list.get(0).trim().split(regexForDimension);
        n = Integer.parseInt(dimensions[0]);
        m = Integer.parseInt(dimensions[1]);

        if (n != (list.size() - 1)) {
            return false;
        }
        for (int i = 1; i <= n; i++) {
            elements = list.get(i).trim().split(" ");
            if (elements.length != m) {
                return false;
            }
            for (int j = 0; j < m; j++) {
                if (!elements[j].matches(regexForDigit)) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Check validity of the transmitted coordinates.
     * @param i -first coordinate.
     * @param j -second coordinate.
     * @param matrix -matrix.
     * @return matrix.
     * */
    boolean checkRange(final Matrix matrix, final int i, final int j) {
        return (i >= 0) && (i <= matrix.getHorizontalSize() - 1) && (j >= 0)
                && (j <= matrix.getVerticalSize() - 1);
    }
    /**
     * Check the size of the matrices.
     * @param matrA -first matrix.
     * @param matrB -second matrix.
     * @return boolean value.
     * */
    public boolean checkDimension(final Matrix matrA, final Matrix matrB) {
        return (matrA.getHorizontalSize() == matrB.getVerticalSize());
    }
    /**
     * Check if matrix are present.
     * @param matrixA      -first matrix.
     * @param matrixB      -second matrix.
     * @return boolean value.
     *  */
    public boolean isPresent(final Optional<Matrix> matrixA,
                             final Optional<Matrix> matrixB) {
        return (!matrixA.isPresent() || !matrixB.isPresent());
    }
}
