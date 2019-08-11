package by.training.multithreading_matrix.entity;

import java.util.Arrays;

/**
 * Represents the essence of an integer matrix.
 * */
public class Matrix {
    /**
     * Integer matrix.
     * */
    private int[][] matr;
    /**
     * Constructor.
     * @param n -count of rows.
     * @param m -count of columns.
     * */
    public Matrix(final int n, final int m) {
        matr = new int[n][m];
    }
    /**
     * @return count of the rows.
     * */
    public int getVerticalSize() {
        return matr.length;
    }
    /**
     * @return count of the columns.
     * */
    public int getHorizontalSize() {
        return matr[0].length;
    }
    /**
     * @return matrix element on (i,j) position.
     * @param i -coordinate.
     * @param j -coordinate.
     * */
    public int getElement(final int i, final int j) {
            return matr[i][j];
    }
    /**
     * @param i -first coordinate.
     * @param j -second coordinate.
     * @param value -value on (i,j) position.
     * */
    public void setElement(final int i, final int j, final int value) {
            matr[i][j] = value;
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
        Matrix matrix = (Matrix) o;
        if (matr.length != matrix.matr.length) {
            return false;
        } else {
            int len = matr.length;
            for (int i = 0; i < len; i++) {
                if (!Arrays.equals(matr[i], matrix.matr[i])) {
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
        return Arrays.hashCode(matr);
    }

    /**toString method.
     * @return string representation of the object.*/
    @Override
    public String toString() {
        StringBuilder s =
                new StringBuilder("\nMatrix : (" + matr.length + "x"
                        + matr[0].length + ")\n");
        for (int[] row : matr) {
            for (int value : row) {
                s.append(value);
                s.append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
