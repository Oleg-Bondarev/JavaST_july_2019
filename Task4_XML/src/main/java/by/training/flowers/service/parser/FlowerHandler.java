package by.training.flowers.service.parser;

import by.training.flowers.entity.ArtificialFlower;
import by.training.flowers.entity.FlowersTagName;
import by.training.flowers.entity.WildFlower;
import by.training.flowers.entity.AbstractFlower;
import by.training.flowers.entity.Multiplying;
import by.training.flowers.entity.Soil;
import by.training.flowers.entity.UnknownTypeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.EnumSet;

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
    private Set<AbstractFlower> allFlowersSet = new LinkedHashSet<>();
    /**
     * Current flower.
     * */
    private AbstractFlower currentFlower = null;
    /**
     * Enum set.
     * */
    private EnumSet<FlowersTagName> abstractFlowerAttribute =
            EnumSet.range(FlowersTagName.FLOWERS,
                    FlowersTagName.SCIENTIST);
    /**
     * Tag enum.
     * */
    private FlowersTagName tagEnum = null;
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
    Set<AbstractFlower> getAllFlowersSet() {
        return allFlowersSet;
    }
    /**
     * End document method.
     * */
    @Override
    public void endDocument() {
        //Nothing doing.
    }
    /**
     * Reports the start of the analysis of the document,
     * provides the application with information about
     * XML element and any attributes.
     *
     * @param uri        - The Namespace URI, or the empty string if the
     *                     element has no Namespace URI or if Namespace
     *                     processing is not being performed.
     * @param localName  - The local name (without prefix), or the
     *                     empty string if Namespace processing is not being
     *                     performed.
     * @param qName      - The qualified name (with prefix), or the
     *                     empty string if qualified names are not available.
     * @param attributes - The attributes attached to the element.  If
     *                     there are no attributes, it shall be an empty
     *                     Attributes object.
     * */
    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attributes) {
        if (FlowersTagName.WILD_FLOWER.getValue().equals(qName)
                || FlowersTagName.ARTIFICIAL_FLOWER.getValue()
                .equals(qName)) {
            if (FlowersTagName.WILD_FLOWER.getValue().equals(qName)) {
                currentFlower = new WildFlower();
            }
            if (FlowersTagName.ARTIFICIAL_FLOWER.getValue().equals(qName)) {
                currentFlower = new ArtificialFlower();
            }
            String id =  attributes.getValue("id");
            String name = attributes.getValue("name");
            boolean isMedicinal =
                Boolean.parseBoolean(attributes.getValue("isMedical"));
            Multiplying multiplying = null;
            if (attributes.getValue("multiplying") == null) {
                multiplying = Multiplying.SEEDS;
            } else {
                try {
                    multiplying = Multiplying.takeMultiplying(attributes
                            .getValue("multiplying"));
                } catch (UnknownTypeException e) {
                    LOGGER.log(Level.WARN, e.getMessage());  //TODO excption
                }
            }
            currentFlower.setIdentificator(id);
            currentFlower.setFlowerName(name);
            currentFlower.setMedical(isMedicinal);
            currentFlower.setMultiplying(multiplying);
        } else {
            FlowersTagName temp =
                    FlowersTagName.valueOf(qName.toUpperCase());
            if (abstractFlowerAttribute.contains(temp)) {
                tagEnum = temp;
            }
        }
    }
    /**
     * Receive notification of the end of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each element (such as finalising a tree node or writing
     * output to a file).</p>
     *
     * @param uri       - The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName - The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     - The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     * */
    @Override
    public void endElement(final String uri, final String localName,
                           final String qName) {
        if (qName.equals(FlowersTagName.WILD_FLOWER.getValue())
                || qName.equals(FlowersTagName.ARTIFICIAL_FLOWER
                .getValue())) {
            allFlowersSet.add(currentFlower);
        }
    }
    /**
     * Receive notification of character data inside an element.
     *
     * By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of character data
     * (such as adding the data to a node or buffer, or printing it to
     * a file).
     *
     * @param ch     - The characters.
     * @param start  - The start position in the character array.
     * @param length - The number of characters to use from the
     *               character array.
     * @throws SAXException -exception.
     * */
    @Override
    public void characters(final char[] ch, final int start, final int length)
            throws SAXException {
        String str = new String(ch, start, length).trim();
        if (tagEnum != null) {
            switch (tagEnum) {
                case FLOWERS:
                case WILD_FLOWER:
                case ARTIFICIAL_FLOWER:
                    break;
                case SOIL:
                    try {
                        currentFlower.setSoil(Soil.takeSoilType(str));
                    } catch (UnknownTypeException e) {
                        LOGGER.log(Level.WARN, e.getMessage()); //TODO exception
                    }
                    break;
                case STEAM_COLOR:
                    currentFlower.setSteamColor(str);
                    break;
                case LEAF_COLOR:
                    currentFlower.setLeafColor(str);
                    break;
                case AVG_SIZE:
                    currentFlower.setAvgSize(Integer.parseInt(str));
                    break;
                case TEMPERATURE:
                    currentFlower.setTemperature(str);
                    break;
                case WATERING:
                    currentFlower.setWatering(Integer.parseInt(str));
                    break;
                case LIGHTING:
                    currentFlower.setLighting(Boolean.parseBoolean(str));
                    break;
                case DISCOVERY_YEAR:
                    currentFlower.setDiscoveryYear(DateParser.parseDate(str));
                    break;
                case IS_PROTECTED:
                    ((WildFlower) currentFlower)
                            .setProtect(Boolean.parseBoolean(str));
                    break;
                case SCIENTIST:
                    ((ArtificialFlower) currentFlower).setScientistName(str);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            tagEnum.getDeclaringClass(), tagEnum.name());
            }
        }
        tagEnum = null;
    }
}
