package by.training.multithreading_matrix.entity;

import by.training.multithreading_matrix.validator.MatrixValidator;

import java.util.Arrays;

/**
 * Represents the essence of an integer matrix.
 * */
public class Matrix {
    /**
     * Integer matrix.
     * */
    private int[][] matrix;
    /**
     * Constructor.
     * @param n -count of rows.
     * @param m -count of columns.
     * */
    public Matrix(final int n, final int m) {
        matrix = new int[n][m];
    }
    /**
     * @return count of the rows.
     * */
    public int getVerticalSize() {
        return matrix.length;
    }
    /**
     * @return count of the columns.
     * */
    public int getHorizontalSize() {
        return matrix[0].length;
    }
    /**
     * @return matrix element on (i,j) position.
     * @param i -coordinate.
     * @param j -coordinate.
     * */
    public int getElement(final int i, final int j) {
            return matrix[i][j];
    }
    /**
     * @param i -first coordinate.
     * @param j -second coordinate.
     * @param value -value on (i,j) position.
     * @throws MatrixException -if have some problems in matrix.
     * */
    public void setElement(final int i, final int j, final int value)
                                        throws MatrixException {
        MatrixValidator validator = new MatrixValidator();
        if (!validator.checkRange(this, i, j)) {
            throw new MatrixException("Incorrect coordinates of element: ("
                    + i + "," + j + ")");
        }
        matrix[i][j] = value;
    }
    /**
     * @param o  -object.
     * @return boolean value
     * */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Matrix m = (Matrix) o;
        if (matrix.length != m.matrix.length) {
            return false;
        } else {
            int len = matrix.length;
            for (int i = 0; i < len; i++) {
                if (!Arrays.equals(matrix[i], m.matrix[i])) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * HashCode.
     * @return int value.
     * */
    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }

    /**toString method.
     * @return string representation of the object.*/
    @Override
    public String toString() {
        StringBuilder s =
                new StringBuilder("\nMatrix : (" + matrix.length + "x"
                        + matrix[0].length + ")\n");
        for (int[] row : matrix) {
            for (int value : row) {
                s.append(value);
                s.append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
