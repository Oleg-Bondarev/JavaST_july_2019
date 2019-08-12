package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.entity.MatrixException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Create matrix with random elements.
 */
class GenerateMatrix {
    /**
     * Random.
     */
    private Random random = new SecureRandom();

    /**
     * Generate elements.
     *
     * @param matrix  -matrix.
     * @param start -start of generation.
     * @param end   -end of generation.
     * @return matrix.
     * @throws ServiceException -have some problems.
     */
    Matrix fillRandomize(final Matrix matrix, final int start,
                          final int end) throws ServiceException {
        int vert = matrix.getVerticalSize();
        int horiz = matrix.getHorizontalSize();
        for (int i = 0; i < vert; ++i) {
            for (int j = 0; j < horiz; ++j) {
                int value = (random.nextInt(end - start) + start);
                try {
                    matrix.setElement(i, j, value);
                } catch (MatrixException e) {
                    throw new ServiceException(e.getMessage());
                }
            }
        }
        return matrix;
    }
}
