package by.training.composite.service.comparator;

import by.training.composite.entity.Component;
import by.training.composite.entity.SentenceComponent;

import java.util.Comparator;

/**
 * Comparator.
 * */
public class CountWordsInSentenceComparator
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
        if ((o1 instanceof SentenceComponent)
                && (o2 instanceof SentenceComponent)) {
            Integer firstSize = o1.amountOfChildren();
            Integer secondSize = o2.amountOfChildren();
            return firstSize.compareTo(secondSize);
        }
        return 0;
    }
}
