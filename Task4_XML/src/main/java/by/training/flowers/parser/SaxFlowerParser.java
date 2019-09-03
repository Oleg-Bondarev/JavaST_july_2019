package by.training.flowers.parser;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
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
     * Schema.
     * */
    private Schema schema;
    /***/
    private String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    /**
     * Schema factory.
     * */
    private SchemaFactory schemaFactory = SchemaFactory.newInstance(constant);
    /**
     * XML reader.
     * */
    private XMLReader reader;
    /**
     * SAX parser factory.
     * */
    private SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    /**
     * @throws ParserException -exception.
     * */
    public SaxFlowerParser() throws ParserException {
        super();
        try {
            schema = schemaFactory.newSchema(
                    new File("data/flowers2.xsd"));
            handler = new FlowerHandler();
            parserFactory.setNamespaceAware(true);
            parserFactory.setValidating(false);
            parserFactory.setSchema(schema);
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
