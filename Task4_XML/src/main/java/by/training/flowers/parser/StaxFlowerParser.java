package by.training.flowers.parser;

import by.training.flowers.entity.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * STAX parser class.
 * */
public class StaxFlowerParser extends AbstractFlowerParser {
    /**
     * XML input factory.
     * */
    private XMLInputFactory inputFactory;
    /**
     * Flowers set.
     * */
    private Set<AbstractFlower> flowerSet = new LinkedHashSet<>();
    /**
     * Constructor.
     * */
    public StaxFlowerParser() {
        super();
        inputFactory = XMLInputFactory.newInstance();
    }
    /**
     * @return flowers set.
     * */
    public Set<AbstractFlower> getFlowerSet() {
        return flowerSet;   //TODO copy?
    }
    /**
     * @param fileName -xml file.
     * @throws ParserException -exception.
     * */
    @Override
    public void buildFlowerSet(final String fileName) throws ParserException {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            //StAX paring
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    AbstractFlower flower = null;
                    if (name.equals(FlowersTagName.WILD_FLOWER.getValue())) {
                        flower = buildWildFlower(reader);
                    }
                    if (name.equals(FlowersTagName.ARTIFICATIONAL_FLOWER
                            .getValue())) {
                        flower = buildArtificialFlower(reader);
                    }
                    if (flower != null) {
                        flowerSet.add(flower);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new ParserException(e);
        }
    }
    /**
     * @param reader -xml stream reader.
     * @return abstract class object.
     * @throws ParserException -exception.
     * @throws XMLStreamException -exception.
     * */
    private AbstractFlower buildWildFlower(final XMLStreamReader reader)
            throws ParserException, XMLStreamException {
        WildFlower wildFlower = new WildFlower();
        /*attributes.*/
        wildFlower.setIdentificator(reader.getAttributeValue(null,
                        FlowersTagName.ID.getValue()));
        wildFlower.setFlowerName(reader.getAttributeValue(null,
                FlowersTagName.NAME.getValue()));
        wildFlower.setMedical(Boolean.parseBoolean(reader.getAttributeValue(null,
                FlowersTagName.IS_MEDICAL.getValue())));
        try {
            if ((reader.getAttributeValue(null,
                    FlowersTagName.MULTIPLYING.getValue())) != null) {
                wildFlower.setMultiplying(Multiplying.takeMultiplying(
                        reader.getAttributeValue(null,
                        FlowersTagName.MULTIPLYING.getValue())));
            } else {
                wildFlower.setMultiplying(Multiplying.SEEDS);
            }
        } catch (UnknownTypeException e) {
            throw new ParserException(e);
        }
        /*content.*/
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    System.out.println(name);
                    switch (FlowersTagName.valueOf(name.toUpperCase())) {
                        case SOIL:
                            try {
                                wildFlower.setSoil(Soil
                                        .takeSoilType(getXMLText(reader)));
                            } catch (UnknownTypeException e) {
                                throw new ParserException(e);
                            }
                            break;
                        case STEAM_COLOR:
                            wildFlower.setSteamColor(getXMLText(reader));
                            break;
                        case LEAF_COLOR:
                            wildFlower.setLeafColor(getXMLText(reader));
                            break;
                        case AVG_SIZE:
                            wildFlower.setAvgSize(Integer
                                    .parseInt(getXMLText(reader)));
                            break;
                        case TEMPERATURE:
                            wildFlower.setTemperature(getXMLText(reader));
                            break;
                        case WATERING:
                            wildFlower.setWatering(Integer
                                    .parseInt(getXMLText(reader)));
                            break;
                        case LIGHTING:
                            wildFlower.setLighting(Boolean.parseBoolean(
                                    getXMLText(reader)));
                            break;
                        case DISCOVERY_YEAR:
                            wildFlower.setDiscoveryYear(DateParser.parseDate(
                                    getXMLText(reader)));
                            break;
                        case IS_PROTECTED:
                            wildFlower.setProtected(Boolean.parseBoolean(
                                    getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowersTagName.valueOf(name.toUpperCase())
                            == FlowersTagName.WILD_FLOWER) {
                        return wildFlower;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag wild flower.");
    }
    /**
     * @param reader -xml stream reader.
     * @return abstract class object.
     * @throws ParserException -exception.
     * @throws XMLStreamException -exception.
     * */
    private AbstractFlower buildArtificialFlower(final XMLStreamReader reader)
            throws ParserException, XMLStreamException {
        ArtificialFlower artificialFlower = new ArtificialFlower();
        /*attributes.*/
        artificialFlower.setIdentificator(reader.getAttributeValue(null,
                FlowersTagName.ID.getValue()));
        artificialFlower.setFlowerName(reader.getAttributeValue(null,
                FlowersTagName.NAME.getValue()));
        artificialFlower.setMedical(Boolean.parseBoolean(reader.getAttributeValue(null,
                FlowersTagName.IS_MEDICAL.getValue())));
        try {
            String tag;
            if ((tag = reader.getAttributeValue(null,
                    FlowersTagName.MULTIPLYING.getValue())) != null) {
                artificialFlower.setMultiplying(Multiplying.takeMultiplying(tag));
            } else {
                artificialFlower.setMultiplying(Multiplying.SEEDS);
            }
        } catch (UnknownTypeException e) {
            throw new ParserException(e);
        }
        /*content.*/
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowersTagName.valueOf(name.toUpperCase())) {
                        case SOIL:
                            try {
                                artificialFlower.setSoil(Soil
                                        .takeSoilType(getXMLText(reader)));
                            } catch (UnknownTypeException e) {
                                throw new ParserException(e);
                            }
                            break;
                        case STEAM_COLOR:
                            artificialFlower.setSteamColor(getXMLText(reader));
                            break;
                        case LEAF_COLOR:
                            artificialFlower.setLeafColor(getXMLText(reader));
                            break;
                        case AVG_SIZE:
                            artificialFlower.setAvgSize(Integer
                                    .parseInt(getXMLText(reader)));
                            break;
                        case TEMPERATURE:
                            artificialFlower.setTemperature(getXMLText(reader));
                            break;
                        case WATERING:
                            artificialFlower.setWatering(Integer
                                    .parseInt(getXMLText(reader)));
                            break;
                        case LIGHTING:
                            artificialFlower.setLighting(Boolean.parseBoolean(
                                    getXMLText(reader)));
                            break;
                        case DISCOVERY_YEAR:
                            artificialFlower.setDiscoveryYear(DateParser.parseDate(
                                    getXMLText(reader)));
                            break;
                        case SCIENTIST_NAME:
                            artificialFlower.setScientistName(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowersTagName.valueOf(name.toUpperCase())
                            == FlowersTagName.ARTIFICATIONAL_FLOWER) {
                        return artificialFlower;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag wild flower.");
    }
    /**
     * @param reader - XML stream reader.
     * @return string of text in tag.
     * @throws XMLStreamException -exception.
     * */
    private String getXMLText(final XMLStreamReader reader)
            throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
