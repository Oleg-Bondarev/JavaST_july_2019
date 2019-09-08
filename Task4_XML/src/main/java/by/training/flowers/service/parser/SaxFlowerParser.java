package by.training.flowers.service.parser;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * SAX parser class.
 * */
public class SaxFlowerParser extends AbstractFlowerParser {
    /**
     * Handler.
     * */
    private FlowerHandler handler;
    /**
     * XML reader.
     * */
    private XMLReader reader;
    /**
     * @throws ParserException -exception.
     * */
    public SaxFlowerParser() throws ParserException {
        super();
        handler = new FlowerHandler();
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            reader = parserFactory.newSAXParser().getXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException | ParserConfigurationException e) {
            throw new ParserException(e);
        }
    }
    /**
     * Parsing method.
     * @param fileName -xml file.
     * @throws ParserException - exception.
     * */
    public void buildFlowerSet(final String fileName) throws ParserException {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            throw new ParserException("SAX parser error: " + e);
        } catch (IOException e) {
            throw new ParserException("I/O thread error: " + e);
        }
        flowersSet = handler.getAllFlowersSet();
    }
}
