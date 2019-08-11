package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.Matrix;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class MultiplicateMatrixTest {
    @DataProvider(name = "ProviderForMatrixMultiplication")
    public Object[][] matrixMultiplicationTest() {

        Matrix mA = new Matrix(3, 3);
        mA.setElement(0,0, 1);
        mA.setElement(0,1, 1);
        mA.setElement(0,2, 1);
        mA.setElement(1,0, 2);
        mA.setElement(1,1, 2);
        mA.setElement(1,2, 2);
        mA.setElement(2,0, 3);
        mA.setElement(2,1, 3);
        mA.setElement(2,2, 3);

        Matrix Mb = new Matrix(3, 2);
        Mb.setElement(0, 0, 1);
        Mb.setElement(0, 1, 1);
        Mb.setElement(1, 0, 2);
        Mb.setElement(1, 1, 2);
        Mb.setElement(2, 0, 3);
        Mb.setElement(2, 1, 3);

        Matrix Mc = new Matrix(3, 2);
        Mc.setElement(0, 0, 6);
        Mc.setElement(0, 1, 6);
        Mc.setElement(1, 0, 12);
        Mc.setElement(1, 1, 12);
        Mc.setElement(2, 0, 18);
        Mc.setElement(2, 1, 18);

        Matrix mA1 = new Matrix(2, 2);
        mA1.setElement(0,0, 1);
        mA1.setElement(0,1, 4);
        mA1.setElement(1,0, 2);
        mA1.setElement(1,1, 3);

        Matrix mB1 = new Matrix(2, 2);
        mB1.setElement(0,0, 5);
        mB1.setElement(0,1, 7);
        mB1.setElement(1,0, 9);
        mB1.setElement(1,1, 2);

        Matrix mC1 = new Matrix(2, 2);
        mC1.setElement(0,0, 41);
        mC1.setElement(0,1, 15);
        mC1.setElement(1,0, 37);
        mC1.setElement(1,1, 20);

        return new Object[][] {
                {mA, Mb, Mc},
                {mA1, mB1, mC1}
        };
    }

    @Test(description = "Test for matrix multiplication",
            dataProvider = "ProviderForMatrixMultiplication")
    public void testMultiplicationByOneThread(Matrix ma, Matrix mb,
                                              Matrix expect) throws ServiceException {
        MultiplicateMatrix multiplicateMatrix = new MultiplicateMatrix();
        Matrix actual = multiplicateMatrix.multiplicationByOneThread(ma, mb);
        assertEquals(actual, expect);
    }
}