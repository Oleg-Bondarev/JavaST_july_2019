package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.service.interfaces.MatrixService;
import by.training.multithreading_matrix.validator.MatrixValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class MatrixServiceImplTest {
    List<Matrix> matrixList = new ArrayList<>();
    private Matrix actual;
    private Matrix expected;
    private Matrix matrixA = new Matrix(2, 2);
    private MatrixServiceImpl service = new MatrixServiceImpl();

    @DataProvider(name = "NegativeDateProviderForValidateMatrixService")
    public Object[][] createNegativeForValidateMatrixService() {
        return new Object[][] {
                {matrixA, matrixA, 0},
                {matrixA, matrixA, -1}
        };
    }
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
    @DataProvider(name = "PositiveForCreateMatrix")
    public Object[][] createPositiveCreateMatrix() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        Matrix mA = new Matrix(2, 2);
        mA.setElement(0,0, 1);
        mA.setElement(0,1, 1);
        mA.setElement(1,0, 2);
        mA.setElement(1,1, 2);

        Matrix mB = new Matrix(2, 2);
        mB.setElement(0,0, 1);
        mB.setElement(0,1, 2);
        mB.setElement(1,0, 2);
        mB.setElement(1,1, 3);

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
    @DataProvider(name = "ProviderForFillZero")
    public Object[][] createfillZero() {
        Matrix mA = new Matrix(3, 3);
        mA.setElement(0,0, 0);
        mA.setElement(0,1, 1);
        mA.setElement(0,2, 1);
        mA.setElement(1,0, 2);
        mA.setElement(1,1, 0);
        mA.setElement(1,2, 2);
        mA.setElement(2,0, 3);
        mA.setElement(2,1, 3);
        mA.setElement(2,2, 0);

        Matrix mA1 = new Matrix(3, 3);
        mA1.setElement(0,0, 1);
        mA1.setElement(0,1, 1);
        mA1.setElement(0,2, 1);
        mA1.setElement(1,0, 2);
        mA1.setElement(1,1, 2);
        mA1.setElement(1,2, 2);
        mA1.setElement(2,0, 3);
        mA1.setElement(2,1, 3);
        mA1.setElement(2,2, 3);

        Matrix mB = new Matrix(2, 2);
        mB.setElement(0,0, 0);
        mB.setElement(0,1, 1);
        mB.setElement(1,0, 2);
        mB.setElement(1,1, 0);

        Matrix mB1 = new Matrix(2, 2);
        mB1.setElement(0,0, 1);
        mB1.setElement(0,1, 1);
        mB1.setElement(1,0, 2);
        mB1.setElement(1,1, 2);

        return new Object[][] {
                {mB, mB1},
                {mA, mA1}
        };
    }


    @Test(description = "Test exception if method get count of threads less " +
            "than 1.", dataProvider =
            "NegativeDateProviderForValidateMatrixService",
            expectedExceptions = ServiceException.class)
    public void testMultiplicateMatrixException(Matrix mA, Matrix mB,
                                           int count) throws ServiceException {
        service.multiplicateMatrix(mA, mB, count);
    }

    @Test(description = "Test exception if method get incorrect matrix " +
            "parameters.", expectedExceptions = ServiceException.class,
            dataProvider = "NegativeForCreateMatrix")
    public void testCreateMatrixException(List list) throws ServiceException {
        service.createMatrix(list);
    }

    @Test(description = "Test creating matrix from list of string.",
            dataProvider = "PositiveForCreateMatrix")
    public void testCreateMatrix(Matrix matrix, List list) throws ServiceException {
        actual = service.createMatrix(list);
        expected = matrix;
        assertEquals(actual, expected);
    }

    @Test(description = "Test for filling matrix diagonal by zero.",
            dataProvider = "ProviderForFillZero")
    public void testFillZeroOnDiagonal(Matrix exp, Matrix matrix) throws ServiceException {
        expected = exp;
        actual = service.fillZeroOnDiagonal(matrix);
        assertEquals(expected, actual);
    }
}