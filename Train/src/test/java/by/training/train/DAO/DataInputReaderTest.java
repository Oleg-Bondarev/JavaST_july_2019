package by.training.train.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataInputReaderTest {
    DataInputReader dataInputReader = new DataInputReader();
    List<String> expectList;
    List<String> actualList;
    final static String passToFile = "src\\main\\resources\\Data\\reader_test.txt";

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
    public void testReadInformationFromFile() throws IOException {
        actualList = dataInputReader.readInformatIonFromFile(passToFile);
        assertEquals(expectList, actualList);
    }
}