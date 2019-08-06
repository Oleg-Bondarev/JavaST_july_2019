package by.training.multithreading_matrix.view;

import by.training.multithreading_matrix.controller.Controller;
import by.training.multithreading_matrix.dao.exceptions.FileReaderException;
import by.training.multithreading_matrix.dao.exceptions.FileWriterException;
import by.training.multithreading_matrix.dao.file_writer.DataOutputWriter;
import by.training.multithreading_matrix.entity.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**Main class.*/
public final class Main {
    /**Def.constr.*/
    private Main() { }
    /**Main method.
     * @param args -program args.
     * @throws FileReaderException -
     * @throws FileWriterException -*/
    public static void main(final String[] args) throws FileReaderException,
            FileWriterException {
       /* DataInputReader reader = new DataInputReader();
        List<String> information = reader.
                readInformationFromFile("data\\input.txt");
        for (String s: information) {
            System.out.println(s);
        }*/

        Controller controller = Controller.getInstance();
        List<String> outputList = new ArrayList<>();
        DataOutputWriter outputWriter = new DataOutputWriter();
        final String outPath1 = "data\\outputA.txt";
        final String outPath2 = "data\\outputB.txt";
        final String outPath3 = "data\\outputC.txt";
        final String outPath4 = "data\\outputNewC.txt";

        Matrix mA = new Matrix(1000, 1000);
        Matrix mB = new Matrix(1000,1000);
        /*Matrix A = new Matrix(2, 4);
        Matrix B = new Matrix(4,3);*/
        mA = controller.generateMatrix(mA, 0, 5);
        mB = controller.generateMatrix(mB, 0, 5);

        outputList.add(mA.toString());
        outputWriter.writeInformationToFile(outPath1, outputList);
        outputList.clear();
        outputList.add(mB.toString());
        outputWriter.writeInformationToFile(outPath2, outputList);

        Matrix mC = controller.multiplicateMatrix(Optional.of(mA),
                Optional.of(mB), 1);

        outputList.clear();
        outputList.add(mC.toString());
        outputWriter.writeInformationToFile(outPath3, outputList);

        Matrix newC = controller.multiplicateMatrix(Optional.of(mA),
                Optional.of(mB), 8);
        outputList.clear();
        outputList.add(newC.toString());
        outputWriter.writeInformationToFile(outPath4, outputList);
    }
}
