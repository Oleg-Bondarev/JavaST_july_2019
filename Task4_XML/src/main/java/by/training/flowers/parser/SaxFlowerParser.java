package by.training.flowers.parser;

import by.training.flowers.entity.AbstractFlower;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * SAX parser class.
 * */
public class SaxFlowerParser {
    /**
     * Set of flowers.
     * */
    private Set<AbstractFlower> flowersSet;
    /**
     * Handler.
     * */
    private FlowerHandler handler;
    /**
     * XML reader.
     * */
    private XMLReader reader;
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
     * SAX parser factory.
     * */
    private SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    /**
     * Constructor.
     * */
    public SaxFlowerParser() { }
    /**
     * @param file -xsd schema.
     * @throws ParserException -exception.
     * */
    public SaxFlowerParser(final File file) throws ParserException {
        try {
            schema = schemaFactory.newSchema(file);
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
     * @return flowers set.
     * */
    public Set<AbstractFlower> getFlowersSet() {
        return flowersSet;  //TODO copy
    }
    /**
     * Parsing method.
     * @param fileName -xml file.
     * @throws ParserException - exception.
     * */
    public void buildFlower(final String fileName) throws ParserException {
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
