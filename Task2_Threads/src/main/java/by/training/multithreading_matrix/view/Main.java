package by.training.multithreading_matrix.view;

import by.training.multithreading_matrix.dao.exceptions.FileReaderException;
import by.training.multithreading_matrix.dao.file_reader.DataInputReader;

import java.util.List;

/**Main class.*/
public final class Main {
    /**Def.constr.*/
    private Main() { }
    /**Main method.
     * @param args -program args.*/
    public static void main(final String[] args) throws FileReaderException {
        DataInputReader reader = new DataInputReader();
        List<String> information = reader.
                readInformationFromFile("E:\\JavaWeb\\JavaST_july_2019\\Task2_Threads\\data\\input.txt");
        for (String s: information) {
            System.out.println(s);
        }

    }
}
