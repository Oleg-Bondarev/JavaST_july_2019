package by.training.flowers.service.parser;

import by.training.flowers.entity.ArtificialFlower;
import by.training.flowers.entity.FlowersTagName;
import by.training.flowers.entity.WildFlower;
import by.training.flowers.entity.AbstractFlower;
import by.training.flowers.entity.Multiplying;
import by.training.flowers.entity.Soil;
import by.training.flowers.entity.UnknownTypeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * DOM parser.
 * */
public class DomFlowerParser extends AbstractFlowerParser {
    /**
     * Document builder object.
     * */
    private DocumentBuilder documentBuilder;
    /**
     * @throws ParserException -if have problems.
     * */
    public DomFlowerParser() throws ParserException {
        this.flowersSet = new LinkedHashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParserException("Exception in DOM configuration: " + e);
        }
    }
    /**
     * @param fileName -input file.
     * @throws ParserException -if have problems.
     * */
    public void buildFlowerSet(final String fileName) throws ParserException {
        Document document;
        try {
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList wildFlowersList = root.getElementsByTagName(
                    FlowersTagName.WILD_FLOWER.getValue());
            NodeList artificialFlowerList =
                    root.getElementsByTagName(FlowersTagName
                            .ARTIFICIAL_FLOWER.getValue());
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
                        FlowersTagName.ARTIFICIAL_FLOWER.getValue());
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
            ((WildFlower) flower).setProtected(Boolean.parseBoolean(
                    getElementTextContent(element,
                            FlowersTagName.IS_PROTECTED.getValue())));
        } else {
            flower = new ArtificialFlower();
            ((ArtificialFlower) flower).setScientistsNames(getXMLScientistList(
                    element));
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
                FlowersTagName.AVG_SIZE.getValue())));
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
    /**
     * @param element -element.
     * @return list of scientists.
     * */
    private static List<String> getXMLScientistList(final Element element) {
        List<String> list = new ArrayList<>();
        Element scientistElement = (Element) element.getElementsByTagName(
                "scientist_name").item(0);
        NodeList nodeList = scientistElement.getElementsByTagName("scientist");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            list.add(node.getTextContent());
        }
        return list;
    }
}
