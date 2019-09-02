package main;

import by.training.flowers.parser.DomFlowerParser;
import by.training.flowers.parser.ParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String[] args) {
        /*try {
            JAXBContext jc = JAXBContext.newInstance(Flowers.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader("data/flowers2.xml");
            Flowers flowers = (Flowers) u.unmarshal(reader);
            System.out.println(flowers.getContent());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        try {
            DomFlowerParser domBuilder = new DomFlowerParser();
            domBuilder.buildSetFlowers("data/flowers2.xml");
            System.out.println(domBuilder.getFlowersSet());
        } catch (ParserException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }

    }
}
