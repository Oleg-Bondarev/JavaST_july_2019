package by.training.flowers.parser;

import by.training.flowers.builder.FlowersTagName;
import by.training.flowers.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Handler class.
 * */
public class FlowerHandler extends DefaultHandler {
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Set of all flowers.
     * */
    private Set<AbstractFlower> allFlowersSet = new HashSet<>();
    /**
     * Current flower.
     * */
    private AbstractFlower currentFlower = null;
    /**
     * Enum set.
     * */
    private EnumSet<FlowersTagName> abstractFlowerAttribute =
            EnumSet.range(FlowersTagName.FLOWERS,
                    FlowersTagName.SCIENTIST_NAME);
    /**
     * Tag enum.
     * */
    private FlowersTagName tagEnum = null;
    /**
     * Class 1.
     * */
    private String className1 = "WildFlower";
    /**
     * Class 2.
     * */
    private String className2 = "ArtificialFlower";

    /**
     * Constructor.
     * */
    public FlowerHandler() {
        super();
    }
    /**
     * Getter.
     * @return set of flowers.
     * */
    public Set<AbstractFlower> getAllFlowersSet() {
        return allFlowersSet; //TODO copy
    }

    public void starDocument() throws SAXException {
        LOGGER.log(Level.INFO, "Parsing SAX start.");
    }

    public void endDocument() throws SAXException {
        LOGGER.log(Level.INFO, "Parsing SAX end.");
    }
    /**
     * Reports the start of the analysis of the document,
     * provides the application with information about
     * XML element and any attributes
     * */
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attributes)
            throws SAXException {
        if (className1.equals(localName) || className2.equals(localName)) {
            if (className1.equals(localName)) {
                currentFlower = new WildFlower();
            }
            if (className2.equals(localName)) {
                currentFlower = new ArtificialFlower();
            }
            String id =  attributes.getValue("id");
            String name = attributes.getValue("name");
            boolean isMedicinal =
                    Boolean.valueOf(attributes.getValue("isMedical"));
            Multiplying multiplying = null;
            try {
                multiplying = Multiplying.takeMultiplying(attributes
                                            .getValue("multiplying"));
            } catch (UnknownTypeException e) {
                e.printStackTrace();  //TODO catching excption
            }

            currentFlower.setIdentificator(id);
            currentFlower.setFlowerName(name);
            currentFlower.setMedical(isMedicinal);
            currentFlower.setMultiplying(multiplying);
        } else {
            FlowersTagName temp = FlowersTagName.valueOf(localName
                    .replace("-","_").toUpperCase());
            if (abstractFlowerAttribute.contains(temp)) {
                tagEnum = temp;
            }
        }
    }

    public void endElement(final String uri, final String localName,
                           final String qName) throws SAXException {;
        if (localName.equals(className1) || localName.equals(className2)) {
            allFlowersSet.add(currentFlower);
        }
    }

    public void characters(final char[] ch, final int start, final int length)
            throws SAXException {
        String str = new String(ch, start, length).trim();
        if (tagEnum != null) {
            switch (tagEnum) {
                case FLOWERS:
                    break;
                case SOIL:
                    try {
                        currentFlower.setSoil(Soil.takeSoilType(str));
                    } catch (UnknownTypeException e) {
                        e.printStackTrace();        //TODO exception
                    }
                    break;
                case STEAM_COLOR:
                    currentFlower.setSteamColor(str);
                    break;
                case LEAF_COLOR:
                    currentFlower.setLeafColor(str);
                    break;
                case AVERAGE_SIZE:
                    currentFlower.setAvgSize(Integer.parseInt(str));
                    break;
                case TEMPERATURE:
                    currentFlower.setTemperature(Integer.parseInt(str));
                    break;
                case WATERING:
                    currentFlower.setWatering(Integer.parseInt(str));
                    break;
                case DISCOVERY_YEAR:
                    currentFlower.setDiscoveryYear(DataParser.parseDate(str));
                    break;
                case IS_PROTECTED:
                    ((WildFlower) currentFlower)
                            .setProtected(Boolean.parseBoolean(str));
                    break;
                case SCIENTIST_NAME:
                    //??
                    break;
                case SCIENTIST:
                    ((ArtificialFlower) currentFlower).setScientistName(str);
                    break;
                default:
                    throw new EnumConstantNotPresentException (
                            tagEnum.getDeclaringClass(), tagEnum.name ());
            }
        }
        tagEnum = null;
    }
}
