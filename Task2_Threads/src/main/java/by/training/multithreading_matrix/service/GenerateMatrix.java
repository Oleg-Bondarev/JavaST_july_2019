package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;

/**Create matrix with random elements.*/
public class GenerateMatrix {
    /**Generate elements.
     * @param matr -matrix.
     * @param start -start of generation.
     * @param end -end of generation.*/
    public Matrix fillRandomizer(final Matrix matr, final int start,
                               final int end) {
        int vert = matr.getVerticalSize();
        int horiz = matr.getHorizontalSize();
        for (int i = 0; i < vert; ++i) {
            for (int j = 0; j < horiz; ++j) {
                int value = (int) (Math.random() * (end - start) + start);
                matr.setElement(i, j, value);
            }
        }
        return matr;
    }
}
