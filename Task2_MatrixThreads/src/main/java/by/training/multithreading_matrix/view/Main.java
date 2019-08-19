package by.training.multithreading_matrix.view;

import by.training.multithreading_matrix.controller.Controller;
import by.training.multithreading_matrix.dao.exceptions.FileReaderException;
import by.training.multithreading_matrix.dao.exceptions.FileWriterException;
import by.training.multithreading_matrix.dao.file_reader.DataInputReader;
import by.training.multithreading_matrix.dao.file_writer.DataOutputWriter;
import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.property.MatrixProperty;
import by.training.multithreading_matrix.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Main class.
 * */
public final class Main {
    /**
     * Default constructor.
     * */
    private Main() { }
    /**
     * Main method.
     * @param args -program args.
     * @throws FileReaderException -if have exception in file reading.
     * @throws FileWriterException -if have exception in writing to file.
     * */
    public static void main(final String[] args) throws FileReaderException,
            FileWriterException {
        DataInputReader reader = new DataInputReader();
        List<String> information = reader.
                readInformationFromFile(MatrixProperty
                                        .PATH_TRANSFORMATION_MATRIX);
        for (String s: information) {
            System.out.println(s);
        }

        Controller controller = Controller.getInstance();
        List<String> outputList = new ArrayList<>();
        DataOutputWriter outputWriter = new DataOutputWriter();
        final String outPath1 = "data\\outputA.txt";
        final String outPath2 = "data\\outputB.txt";
        final String outPath3 = "data\\outputC.txt";
        final String outPath4 = "data\\outputNewC.txt";
        final int dim = 2000;
        final int bound = 5;
        final int countThreads = 8;
        Matrix mA = new Matrix(dim, dim);
        Matrix mB = new Matrix(dim, dim);
        try {
            mA = controller.generateMatrix(mA, 0, bound);
            mB = controller.generateMatrix(mB, 0, bound);
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        /*outputList.add(mA.toString());
        outputWriter.writeInformationToFile(outPath1, outputList);
        outputList.clear();
        outputList.add(mB.toString());
        outputWriter.writeInformationToFile(outPath2, outputList);*/

       /* Matrix mC = controller.multiplicateMatrix(Optional.of(mA),
                Optional.of(mB), 1);*/

        /*outputList.clear();
        outputList.add(mC.toString());
        outputWriter.writeInformationToFile(outPath3, outputList);*/

        /*Matrix newC = controller.multiplicateMatrix(Optional.of(mA),
                Optional.of(mB), countThreads);*/
        /*outputList.clear();
        outputList.add(newC.toString());
        outputWriter.writeInformationToFile(outPath4, outputList);*/

        //second way
        /*Matrix newCSecondWay = controller.multiplicateMatrixSecondWay(
                Optional.of(mA), Optional.of(mB), countThreads);*/
        /*outputList.clear();
        outputList.add(newC.toString());
        outputWriter.writeInformationToFile(outPath3, outputList);*/

        Matrix matr = controller.ctreateMatrixFromFile(MatrixProperty
                                            .PATH_TRANSFORMATION_MATRIX);
        //System.out.println(matr);
        matr = controller.workWithDiagonal(Optional.of(matr));
        System.out.println(matr);

    }
}
