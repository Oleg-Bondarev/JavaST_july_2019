package by.training.flowers.parser;

import by.training.flowers.entity.AbstractFlower;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Abstract class.
 * */
public abstract class AbstractFlowerParser {
    /**
     * Set of flowers.
     * */
    protected Set<AbstractFlower> flowersSet;
    /**
     * Constructor.
     * */
    public AbstractFlowerParser() {
        this.flowersSet = new LinkedHashSet<>();
    }
    /**
     * @param set -new set.
     * */
    public AbstractFlowerParser(final Set<AbstractFlower> set) {
        this.flowersSet = set;
    }
    /**
     * @return set.
     * */
    public Set<AbstractFlower> getFlowersSet() {
        return flowersSet;  //TODO copy
    }
    /**
     * Abstract method.
     * @param fileName -file.
     * @throws ParserException - exception.
     * */
    public abstract void buildFlowerSet(String fileName) throws ParserException;
}
