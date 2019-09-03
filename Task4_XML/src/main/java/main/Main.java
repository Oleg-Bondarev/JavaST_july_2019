package main;

import by.training.flowers.entity.AbstractFlower;
import by.training.flowers.factory.FlowerBuilderFactory;
import by.training.flowers.factory.UnknownParserTypeException;
import by.training.flowers.parser.AbstractFlowerParser;
import by.training.flowers.parser.ParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Main class.
 * */
public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(final String[] args) {
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
        FlowerBuilderFactory flowerFactory = FlowerBuilderFactory.getInstance();
        //DOM
//        try {
//            AbstractFlowerParser domBuilder =
//                    flowerFactory.createFlowerBuilder("DOM");
//            domBuilder.buildFlowerSet("data/flowers2.xml");
//            System.out.println(domBuilder.getFlowersSet());
//        } catch (ParserException | UnknownParserTypeException e) {
//            LOGGER.log(Level.WARN, e.getMessage());
//        }

        //SAX
//        try {
//            AbstractFlowerParser saxFlowerParser =
//                    flowerFactory.createFlowerBuilder("SAX");
//            saxFlowerParser.buildFlowerSet("data/flowers2.xml");
//            System.out.println(saxFlowerParser.getFlowersSet());
//        } catch (ParserException | UnknownParserTypeException e) {
//            LOGGER.log(Level.WARN, e.getMessage());
//        }

        //StAX
        try {
            AbstractFlowerParser staxFlowerParser =
                    flowerFactory.createFlowerBuilder("STAX");
            staxFlowerParser.buildFlowerSet("data/flowers2.xml");
            System.out.println(staxFlowerParser.getFlowersSet());
        } catch (UnknownParserTypeException | ParserException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }
    }
}
