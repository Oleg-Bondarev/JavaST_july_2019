package by.training.train.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.training.train.dao.exception.FileReaderException;
import by.training.train.dao.file_reader.DataInputReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataInputReaderTest {
    DataInputReader dataInputReader = new DataInputReader();
    List<String> expectList;
    List<String> actualList;
    final static String passToFile = "E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\reader_test.txt";

    @BeforeMethod
    public void setUp() {
        String[] expectArray = {"COMPARTMENT|2|58|VIP|25.5|9",
                "ECONOM|2|70|BUISINESS|20.1|9|RESERVEDSEAT",
                "SITING|3|40|LUX|34.6|TRUE",
                "RESTARAUNT|10|0|ECONOM|0.0|50"};
        expectList = Arrays.asList(expectArray);
        actualList = new ArrayList<>();
    }

    @Test
    public void testReadInformationFromFile() throws FileReaderException {
        actualList = dataInputReader.readInformationFromFile(passToFile);
        assertEquals(expectList, actualList);
    }
}