package by.training.composite.service.comparator;

import by.training.composite.entity.Component;
import by.training.composite.entity.LexemeComponent;

import java.util.Comparator;

/**
 * Comparator.
 * */
public class WordsLengthComparator
        implements Comparator<Component> {
    /**
     * Compare method.
     * @param o1 -first component.
     * @param o2 -second component.
     * @return difference.
     * */
    @Override
    public int compare(final Component o1,
                       final Component o2) {
        if ((o1 instanceof LexemeComponent)
                && (o2 instanceof LexemeComponent)) {
            Integer firstSize = o1.getChild(0).amountOfChildren();
            Integer secondSize = o2.getChild(0).amountOfChildren();
            return firstSize.compareTo(secondSize);
        }
        return 0;
    }
}
