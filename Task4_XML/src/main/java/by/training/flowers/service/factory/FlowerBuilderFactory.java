package by.training.flowers.service.factory;

import by.training.flowers.service.parser.DomFlowerParser;
import by.training.flowers.service.parser.SaxFlowerParser;
import by.training.flowers.service.parser.StaxFlowerParser;
import by.training.flowers.service.parser.AbstractFlowerParser;
import by.training.flowers.service.parser.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

/**
 * Factory for parsers.
 * */
public final class FlowerBuilderFactory {
    private static final Logger LOG = LogManager.getLogger();
    /**
     * Parsers types.
     * */
    private enum ParserType { DOM, SAX, STAX }
    /**
     * Instance.
     * */
    private static final FlowerBuilderFactory INSTANCE =
            new FlowerBuilderFactory();
    /**
     * Factory that organized like a map.
     * */
    private final Map<ParserType, AbstractFlowerParser> factory =
            new EnumMap<>(ParserType.class);
    /**
     * Constructor.
     * //@throws ParserException -exception.
     * */
    private FlowerBuilderFactory() {
        try {
            factory.put(ParserType.DOM, new DomFlowerParser());
            factory.put(ParserType.SAX, new SaxFlowerParser());
            factory.put(ParserType.STAX, new StaxFlowerParser());
        } catch (ParserException e) {
            e.printStackTrace(); //TODO check
        }
    }
    /**
     * @return class instance.
     * */
    public static FlowerBuilderFactory getInstance() {
        return INSTANCE;
    }
    /**
     * Factory.
     * @param parser -parser name.
     * @return concrete parser object.
     * @throws UnknownParserTypeException -unknown parser.
     * */
    public AbstractFlowerParser createFlowerBuilder(final String parser)
            throws UnknownParserTypeException {
        LOG.info("parser="+parser);
        ParserType type = ParserType.valueOf(parser.toUpperCase());
        if (factory.containsKey(type)) {
            return factory.get(type);
        } else {
            throw new UnknownParserTypeException("Unknown parser type: " + type);
        }
    }
}
