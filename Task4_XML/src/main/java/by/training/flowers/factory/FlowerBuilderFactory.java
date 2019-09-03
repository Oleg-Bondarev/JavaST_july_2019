package by.training.flowers.factory;

import by.training.flowers.parser.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for parsers.
 * */
public final class FlowerBuilderFactory {
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
            new HashMap<>();
    /**
     * Constructor.
     * @throws ParserException -exception.
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
        ParserType type = ParserType.valueOf(parser.toUpperCase());
       if (factory.containsKey(type)) {
           return factory.get(type);
       } else {
           throw new UnknownParserTypeException("Unknown parser type: " + type);
       }
    }
}
