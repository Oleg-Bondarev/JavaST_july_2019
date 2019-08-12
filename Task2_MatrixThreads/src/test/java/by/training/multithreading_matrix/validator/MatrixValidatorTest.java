package by.training.multithreading_matrix.validator;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.service.MatrixServiceImpl;
import by.training.multithreading_matrix.service.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Class for testing matrix validation.
 * */
public class MatrixValidatorTest {
    /**
     * Matrix validator object.
     * */
    private MatrixValidator validator = new MatrixValidator();
    /**
     * Data provider with negative scenario to validate matrix dimension.
     * @return two-dimension array with data for testing.
     * */
    @DataProvider(name = "NegativeForValidateDimension")
    public Object[] createNegativeForValidateStr() {
        return new String[]{
                "344g678",
                "hefuiergf",
                "121212"
        };
    }
    /**
     * Data provider with positive scenario to validate matrix dimension.
     * @return two-dimension array with data for testing.
     * */
    @DataProvider(name = "PositiveForValidateDimension")
    public Object[] createPositivetiveForvalidateStr() {
        return new String[]{
                "344*678",
                "1*1"
        };
    }
    /**
     * Testing incorrect matrix dimension.
     * @param str -string with matrix dimension.
     * */
    @Test(description = "Incorrect dimension of the matrix",
            dataProvider = "NegativeForValidateDimension")
    public void testValidateIncorrectMatrixDimension(final String str) {
        List<String> list = new ArrayList<>();
        list.add(str);
        boolean actual = validator.validateMatrix(list);
        assertFalse(actual);
    }
    /**
     * Testing correct matrix dimension.
     * @param str -string with matrix dimension.
     * */
    @Test(description = "Correct dimension of the matrix",
            dataProvider = "PositiveForValidateDimension")
    public void testValidateCorrectMatrixDimension(final String str) {
        List<String> list = new ArrayList<>();
        list.add(str);
        boolean actual = validator.validateMatrix(list);
        assertFalse(actual);
    }
    /**
     * Testing incorrect number of rows in input matrix.
     * */
    @Test(description = "If matrix has count of rows less than dimension.")
    public void testNegativeForValidateCountRows() {
        List<String> list = new ArrayList<>();
        list.add("2*2");
        list.add("1 4");
        boolean actual = validator.validateMatrix(list);
        assertFalse(actual);
    }
    /**
     * Testing incorrect element in the matrix.
     * */
    @Test(description = "If matrix has not a digit element.")
    public void testNegativeForValidateNotADigit() {
        List<String> list = new ArrayList<>();
        list.add("2*2");
        list.add("1 4");
        list.add("egfe 4");
        boolean actual = validator.validateMatrix(list);
        assertFalse(actual);
    }
    /**
     * Testing coordinates of set element.
     * */
    @Test(description = "Element coordinates are in range.")
    public void testCheckRangePositive() {
        Matrix matrix = new Matrix(2, 2);
        MatrixServiceImpl matrixService = new MatrixServiceImpl();
        try {
            matrix = matrixService.generateMatrix(matrix, 1, 10);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        boolean actual = validator.checkRange(matrix, 1, 0);
        assertTrue(actual);
    }
    /**
     * Testing coordinates of set element.
     * */
    @Test(description = "Element coordinates are not in range.")
    public void testCheckRangeNegative() {
        Matrix matrix = new Matrix(2, 2);
        MatrixServiceImpl matrixService = new MatrixServiceImpl();
        try {
            matrix = matrixService.generateMatrix(matrix, 1, 10);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        boolean actual = validator.checkRange(matrix, 1, 2);
        assertFalse(actual);
    }
    /**
     * Testing the correctness of the dimensions of the matrices for
     * multiplication.
     * */
    @Test(description = "Count of rows in A equals count of columns in B.")
    public void testCheckDimensionPositive() {
        Matrix matrixA = new Matrix(2, 2);
        Matrix matrixB = new Matrix(2, 4);
        boolean actual = validator.checkDimension(matrixA, matrixB);
        assertTrue(actual);
    }
    /**
     * Testing the correctness of the dimensions of the matrices for
     * multiplication.
     * */
    @Test(description = "Count of rows in A not equals count of columns in B.")
    public void testCheckDimensionNegative() {
        Matrix matrixA = new Matrix(2, 2);
        Matrix matrixB = new Matrix(3, 4);
        boolean actual = validator.checkDimension(matrixA, matrixB);
        assertFalse(actual);
    }
}
