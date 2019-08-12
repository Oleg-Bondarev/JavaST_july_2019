package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.entity.MatrixException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Class for testing matrix service.
 * */
public class MatrixServiceImplTest {
    /**
     * Actual matrix.
     * */
    private Matrix actual;
    /**
     * Expected matrix.
     * */
    private Matrix expected;
    /**
     * Matrix service.
     * */
    private MatrixServiceImpl service = new MatrixServiceImpl();
    /**
     * Data provider with negative scenario for matrix validate service.
     * @return two-dimension array with data for test.
     * */
    @DataProvider(name = "NegativeDateProviderForValidateMatrixService")
    public Object[][] createNegativeForValidateMatrixService() {
        Matrix matrixA = new Matrix(2, 2);
        return new Object[][] {
                {matrixA, matrixA, 0},
                {matrixA, matrixA, -1}
        };
    }
    /**
     * Data provider with negative scenario for matrix creating.
     * @return two-dimension array with data for test.
     * */
    @DataProvider(name = "NegativeForCreateMatrix")
    public Object[] createNegativeCreateMatrix() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("2*2");
        list1.add("1 1");

        list2.add("2*2");
        list2.add("1 2");
        list2.add("2 temp");
        return new Object[] {
                list1,
                list2
        };
    }
    /**
     * Data provider with positive scenario for matrix creating.
     * @return two-dimension array with data for test.
     * */
    @DataProvider(name = "PositiveForCreateMatrix")
    public Object[][] createPositiveCreateMatrix() {
        final int three = 3;
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        Matrix mA = new Matrix(2, 2);
        Matrix mB = new Matrix(2, 2);
        try {
            mA.setElement(0, 0, 1);
            mA.setElement(0, 1, 1);
            mA.setElement(1, 0, 2);
            mA.setElement(1, 1, 2);

            mB.setElement(0, 0, 1);
            mB.setElement(0, 1, 2);
            mB.setElement(1, 0, 2);
            mB.setElement(1, 1, three);
        } catch (MatrixException e) {
            e.printStackTrace();
        }

        list1.add("2*2");
        list1.add("1 1");
        list1.add("2 2");

        list2.add("2*2");
        list2.add("1 2");
        list2.add("2 3");
        return new Object[][] {
                {mA, list1},
                {mB, list2}
        };
    }
    /**
     * Data provider with data for test filling the diagonal with zero.
     * @return two-dimension array with data for test.
     * */
    @DataProvider(name = "ProviderForFillZero")
    public Object[][] createFillZero() {
        final int three = 3;
        Matrix mA = new Matrix(three, three);
        Matrix mA1 = new Matrix(three, three);
        Matrix mB = new Matrix(2, 2);
        Matrix mB1 = new Matrix(2, 2);
        try {
            mA.setElement(0, 0, 0);
            mA.setElement(0, 1, 1);
            mA.setElement(0, 2, 1);
            mA.setElement(1, 0, 2);
            mA.setElement(1, 1, 0);
            mA.setElement(1, 2, 2);
            mA.setElement(2, 0, three);
            mA.setElement(2, 1, three);
            mA.setElement(2, 2, 0);

            mA1.setElement(0, 0, 1);
            mA1.setElement(0, 1, 1);
            mA1.setElement(0, 2, 1);
            mA1.setElement(1, 0, 2);
            mA1.setElement(1, 1, 2);
            mA1.setElement(1, 2, 2);
            mA1.setElement(2, 0, three);
            mA1.setElement(2, 1, three);
            mA1.setElement(2, 2, three);

            mB.setElement(0, 0, 0);
            mB.setElement(0, 1, 1);
            mB.setElement(1, 0, 2);
            mB.setElement(1, 1, 0);

            mB1.setElement(0, 0, 1);
            mB1.setElement(0, 1, 1);
            mB1.setElement(1, 0, 2);
            mB1.setElement(1, 1, 2);
        } catch (MatrixException e) {
            e.printStackTrace();
        }
        return new Object[][] {
                {mB, mB1},
                {mA, mA1}
        };
    }
    /**
     * Testing throwing errors in a method.
     * @param mA first matrix.
     * @param mB second matrix.
     * @param count count of running threads.
     * @throws ServiceException if have some problems with input data.
     * */
    @Test(description = "Test exception if method get count of threads less "
            + "than 1.", dataProvider =
            "NegativeDateProviderForValidateMatrixService",
            expectedExceptions = ServiceException.class)
    public void testMultiplicationMatrixException(final Matrix mA,
                                                  final Matrix mB,
                                                  final int count)
                                                    throws ServiceException {
        service.multiplicateMatrix(mA, mB, count);
    }
    /**
     * Testing throwing errors in a method.
     * @param list -list with information for matrix creation.
     * @throws ServiceException if have some problems with input data.
     * */
    @Test(description = "Test exception if method get incorrect matrix "
            + "parameters.", expectedExceptions = ServiceException.class,
            dataProvider = "NegativeForCreateMatrix")
    public void testCreateMatrixException(final List<String> list)
            throws ServiceException {
        service.createMatrix(list);
    }
    /**
     * Testing matrix creation from list of string.
     * @param matrix -expect matrix.
     * @param list -list with data for matrix creation.
     * @throws ServiceException if have some problems with input data.
     * */
    @Test(description = "Test creating matrix from list of string.",
            dataProvider = "PositiveForCreateMatrix")
    public void testCreateMatrix(final Matrix matrix, final List<String> list)
                                                throws ServiceException {
        actual = service.createMatrix(list);
        expected = matrix;
        assertEquals(actual, expected);
    }
    /**
     * Testing filling matrix diagonal with zero.
     * @param matrix -source matrix.
     * @param exp -expect matrix.
     * @throws ServiceException if have some problems with input data.
     * */
    @Test(description = "Test for filling matrix diagonal by zero.",
            dataProvider = "ProviderForFillZero")
    public void testFillZeroOnDiagonal(final Matrix exp, final Matrix matrix)
                                                throws ServiceException {
        expected = exp;
        actual = service.fillZeroOnDiagonal(matrix);
        assertEquals(expected, actual);
    }
}
