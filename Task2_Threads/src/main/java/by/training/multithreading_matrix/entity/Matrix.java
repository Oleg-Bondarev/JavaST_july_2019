package by.training.multithreading_matrix.entity;

/**Represents the essence of an integer matrix*/
public class Matrix {
    /**Integer matrix*/
    private int[][] matrix;
    /**Constructor.
     * @param n -count of rows.
     * @param m -count of columns.
     * */
    public Matrix(final int n, final int m) {
        matrix = new int[n][m];
    }
    /**@return count of the rows.*/
    public int getVerticalSize() {
        return matrix.length;
    }
    /**@return count of the columns.*/
    public int getHorizontalSize() {
        return matrix[0].length;
    }
    /**@return matrix element on (i,j) position.
     * */
    public int getElement(int i, int j) {
            return matrix[i][j];
    }
    /**@param i -first coordinate.
     * @param j -second coordinate.
     * @param value -value on (i,j) position.
     * */
    public void setElement(int i, int j, int value) {
            matrix[i][j] = value;
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
                s.append(value + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
