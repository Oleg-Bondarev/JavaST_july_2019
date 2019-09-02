package by.training.flowers.parser;

import by.training.flowers.builder.FlowersTagName;
import by.training.flowers.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * DOM parser.
 * */
public class DomFlowerParser {
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Set.
     * */
    private Set<AbstractFlower> flowersSet;
    /**
     * Document builder object.
     * */
    private DocumentBuilder documentBuilder;
    /**
     * @return set.
     * */
    public Set<AbstractFlower> getFlowersSet() {
        return flowersSet; //TODO copy?
    }
    /**
     * @throws ParserException -if have problems.
     * */
    public DomFlowerParser() throws ParserException {
        this.flowersSet = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, "Exception in DOM configuration: {}", e);
            throw new ParserException("Exception in DOM configuration: " + e);
        }
    }
    /**
     * @param fileName -input file.
     * @throws ParserException -if have problems.
     * */
    public void buildSetFlowers(final String fileName) throws ParserException {
        Document document = null;
        try {
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList wildFlowersList = root.getElementsByTagName("WildFlower");
            NodeList artificialFlowerList =
                    root.getElementsByTagName("ArtificialFlower");
            int len = wildFlowersList.getLength();
            for (int i = 0; i <  len; i++) {
                Element wildFlowerElement = (Element) wildFlowersList.item(i);
                WildFlower wildFlower = (WildFlower)
                        buildFlower(wildFlowerElement,
                        FlowersTagName.WILD_FLOWER.getValue());
                flowersSet.add(wildFlower);
            }
            len = artificialFlowerList.getLength();
            for (int i = 0; i < len; i++) {
                Element artificialFlowerElement =
                        (Element) artificialFlowerList.item(i);
                ArtificialFlower artificialFlower =
                        (ArtificialFlower) buildFlower(artificialFlowerElement,
                        FlowersTagName.ARTIFICATIONAL_FLOWER.getValue());
                flowersSet.add(artificialFlower);
            }
        } catch (SAXException | IOException e) {
            throw new ParserException(e);
        }
    }
    /**
     * @param element -element.
     * @param tag -name.
     * @throws ParserException -if have problems.
     * @return abstract flower object.
     * */
    private AbstractFlower buildFlower(final Element element,
                                       final String tag)
            throws ParserException {
        AbstractFlower flower;
        if (tag.equals(FlowersTagName.WILD_FLOWER.getValue())) {
            flower = new WildFlower();
            //TODO check
            ((WildFlower) flower).setProtected(Boolean.parseBoolean(
                    getElementTextContent(element,
                            FlowersTagName.IS_PROTECTED.getValue())));
        } else {
            flower = new ArtificialFlower();
            ((ArtificialFlower) flower).setScientistName(
                    getElementTextContent(element,
                            FlowersTagName.SCIENTIST_NAME.getValue()));
        }
        if (element.getAttribute(FlowersTagName.MULTIPLYING.getValue())
                .isEmpty()) {
            flower.setMultiplying(Multiplying.SEEDS);
        } else {
            try {
                flower.setMultiplying(Multiplying.takeMultiplying(element
                        .getAttribute(FlowersTagName.MULTIPLYING.getValue())));
            } catch (UnknownTypeException e) {
                throw new ParserException(e);
            }
        }
        flower.setIdentificator(element.getAttribute(FlowersTagName.ID.
                getValue()));
        flower.setFlowerName(element.getAttribute(FlowersTagName.NAME
                .getValue()));
        flower.setMedical(Boolean.parseBoolean(element.getAttribute(
                FlowersTagName.IS_MEDICAL.getValue())));
        try {
            flower.setSoil(Soil.takeSoilType(getElementTextContent(element,
                    FlowersTagName.SOIL.getValue())));
        } catch (UnknownTypeException e) {
            throw new ParserException(e);
        }
        flower.setSteamColor(getElementTextContent(element,
                FlowersTagName.STEAM_COLOR.getValue()));
        flower.setLeafColor(getElementTextContent(element,
                FlowersTagName.LEAF_COLOR.getValue()));
        flower.setAvgSize(Integer.parseInt(getElementTextContent(element,
                FlowersTagName.AVERAGE_SIZE.getValue())));
        flower.setTemperature(getElementTextContent(element,
                FlowersTagName.TEMPERATURE.getValue()));
        flower.setWatering(Integer.parseInt(getElementTextContent(element,
                FlowersTagName.WATERING.getValue())));
        flower.setLighting(Boolean.parseBoolean(getElementTextContent(element,
                FlowersTagName.LIGHTING.getValue())));
        flower.setDiscoveryYear(DateParser.parseDate(getElementTextContent(
                element, FlowersTagName.DISCOVERY_YEAR.getValue())));
        return flower;
    }
    /**
     * @param element -element.
     * @param elementName -name.
     * @return string.
     * */
    private static String getElementTextContent(final Element element,
                                                final String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
