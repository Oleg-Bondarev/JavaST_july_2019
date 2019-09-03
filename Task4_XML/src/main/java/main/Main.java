package main;

import by.training.flowers.service.factory.FlowerBuilderFactory;
import by.training.flowers.service.factory.UnknownParserTypeException;
import by.training.flowers.service.parser.AbstractFlowerParser;
import by.training.flowers.service.parser.ParserException;
import by.training.flowers.service.properties.PropertiesReader;
import by.training.flowers.service.properties.PropertyException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        String propertyPath = "property/config.properties";
        String pathXML = null;
        try {
            pathXML = PropertiesReader
                    .takeProperty(propertyPath, "fileXML");
        } catch (PropertyException e) {
            e.printStackTrace();
        }
        //DOM
//        try {
//            AbstractFlowerParser domBuilder =
//                    flowerFactory.createFlowerBuilder("DOM");
//            domBuilder.buildFlowerSet(pathXML);
//            System.out.println(domBuilder.getFlowersSet());
//        } catch (ParserException | UnknownParserTypeException e) {
//            LOGGER.log(Level.WARN, e.getMessage());
//        }

        //SAX
//        try {
//            AbstractFlowerParser saxFlowerParser =
//                    flowerFactory.createFlowerBuilder("SAX");
//            saxFlowerParser.buildFlowerSet(pathXML);
//            System.out.println(saxFlowerParser.getFlowersSet());
//        } catch (ParserException | UnknownParserTypeException e) {
//            LOGGER.log(Level.WARN, e.getMessage());
//        }

        //StAX
        try {
            AbstractFlowerParser staxFlowerParser =
                    flowerFactory.createFlowerBuilder("STAX");
            staxFlowerParser.buildFlowerSet(pathXML);
            System.out.println(staxFlowerParser.getFlowersSet());
        } catch (UnknownParserTypeException | ParserException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }
    }
}
