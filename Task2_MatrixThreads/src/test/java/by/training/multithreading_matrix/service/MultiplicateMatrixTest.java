package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.entity.MatrixException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Class for testing matrix multiplication.
 * */
public class MultiplicateMatrixTest {
    /**
     * Data provider for matrix multiplication.
     * @return two-dimension array with data for testing.
     * */
    @DataProvider(name = "ProviderForMatrixMultiplication")
    public Object[][] matrixMultiplicationTest() {
        Matrix matrixA = new Matrix(3, 3);
        Matrix matrixB = new Matrix(3, 2);
        Matrix matrixC = new Matrix(3, 2);
        Matrix matrixA1 = new Matrix(2, 2);
        Matrix matrixB1 = new Matrix(2, 2);
        Matrix matrixC1 = new Matrix(2, 2);
        try {
            matrixA.setElement(0, 0, 1);
            matrixA.setElement(0, 1, 1);
            matrixA.setElement(0, 2, 1);
            matrixA.setElement(1, 0, 2);
            matrixA.setElement(1, 1, 2);
            matrixA.setElement(1, 2, 2);
            matrixA.setElement(2, 0, 3);
            matrixA.setElement(2, 1, 3);
            matrixA.setElement(2, 2, 3);

            matrixB.setElement(0, 0, 1);
            matrixB.setElement(0, 1, 1);
            matrixB.setElement(1, 0, 2);
            matrixB.setElement(1, 1, 2);
            matrixB.setElement(2, 0, 3);
            matrixB.setElement(2, 1, 3);

            matrixC.setElement(0, 0, 6);
            matrixC.setElement(0, 1, 6);
            matrixC.setElement(1, 0, 12);
            matrixC.setElement(1, 1, 12);
            matrixC.setElement(2, 0, 18);
            matrixC.setElement(2, 1, 18);

            matrixA1.setElement(0, 0, 1);
            matrixA1.setElement(0, 1, 4);
            matrixA1.setElement(1, 0, 2);
            matrixA1.setElement(1, 1, 3);

            matrixB1.setElement(0, 0, 5);
            matrixB1.setElement(0, 1, 7);
            matrixB1.setElement(1, 0, 9);
            matrixB1.setElement(1, 1, 2);

            matrixC1.setElement(0, 0, 41);
            matrixC1.setElement(0, 1, 15);
            matrixC1.setElement(1, 0, 37);
            matrixC1.setElement(1, 1, 20);

        } catch (MatrixException e) {
            e.printStackTrace();
        }
        return new Object[][] {
                {matrixA, matrixB, matrixC},
                {matrixA1, matrixB1, matrixC1}
        };
    }
    /**
     * Testig matrix multiplication.
     * @param matrixA -first matrix.
     * @param matrixB -second matrix.
     * @param expect -expect result matrix.
     * @throws ServiceException if have some problems with input data.
     * */
    @Test(description = "Test for matrix multiplication",
            dataProvider = "ProviderForMatrixMultiplication")
    public void testMultiplicationByOneThread(final Matrix matrixA,
                                              final Matrix matrixB,
                                              final Matrix expect)
                                              throws ServiceException {
        MultiplicateMatrix multiplicateMatrix = new MultiplicateMatrix();
        Matrix actual = multiplicateMatrix.multiplicationByOneThread(matrixA,
                matrixB);
        assertEquals(actual, expect);
    }
}
