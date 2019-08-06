package by.training.multithreading_matrix.validator;

import by.training.multithreading_matrix.entity.Matrix;

import java.util.List;

/**Validate parameters of the matrix.*/
public class MatrixValidator {
    /**@param list -list with dimension and elements of the matrix.
     * @return boolean value.*/
    public boolean validateMatrix(final List<String> list) {
        int n;
        int m;
        String[] elements;
        final String regexForDigit = "\\d+";
        String[] dimensions = list.get(0).trim().split("\\*");
        n = Integer.parseInt(dimensions[0]);
        m = Integer.parseInt(dimensions[1]);

        if (n != (list.size() - 1)) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            elements = list.get(i).trim().split(" ");
            if (elements.length != m) {
                return false;
            }
            for (int j = 0; j < m; ++j) {
                if (!elements[j].matches(regexForDigit)) {
                    return false;
                }
            }
        }
        return true;
    }
    /**Check validity of the transmitted coordinates.
     * @param i -first coordinate.
     * @param j -second coordinate.
     * @param matrix -matrix.
     * @return matrix.*/
    public boolean checkRange(final Matrix matrix, final int i, final int j) {
        return (i >= 0) && (i <= matrix.getHorizontalSize() - 1) && (j >= 0)
                && (j <= matrix.getVerticalSize() - 1);
    }
    /**checks the size of the matrices.
     * @param matrA -first matrix.
     * @param matrB -second matrix.
     * @return boolean value.*/
    public boolean checkDimension(final Matrix matrA, final Matrix matrB) {
        return (matrA.getHorizontalSize() == matrB.getVerticalSize());
    }
}
