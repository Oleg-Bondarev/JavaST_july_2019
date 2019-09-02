package by.training.composite.service.sort;

import by.training.composite.entity.Component;
import by.training.composite.service.interfaces.SortSpecification;
import by.training.composite.service.comparator.CountWordsInSentenceComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sorting sentencies by count of words.
 */
public class SortSentInParagraphByCountWords
        implements SortSpecification<Component> {
    /**
     * @return comparator.
     * */
    @Override
    public Comparator<Component> getComparator() {
        return new CountWordsInSentenceComparator();
    }
    /**
     * Sort method.
     * @param newComponent -component.
     * */
    @Override
    public void sort(final Component newComponent) {
        int countChildren = newComponent.amountOfChildren();
        for (int i = 0; i < countChildren; ++i) {
            sortSentences(newComponent.getChild(i));
        }
    }
    /**
     * Sorting sentences.
     * @param newComponent -component.
     * */
    private void sortSentences(final Component newComponent) {
        List<Component> componentList = new ArrayList<>();
        int countChildren = newComponent.amountOfChildren();
        for (int i = 0; i < countChildren; ++i) {
            componentList.add(newComponent.getChild(i));
        }
        componentList.sort(this.getComparator());
        removeAll(componentList, newComponent);
        addAll(componentList, newComponent);
    }
    /**
     * Method add all  components in tree.
     * @param newComponentsList -components list.
     * @param newComponent -new component.
     * */
    private void addAll(final List<Component> newComponentsList,
                        final Component newComponent) {
        for (Component component : newComponentsList) {
            newComponent.add(component);
        }
    }
    /**
     * Method remove all components from tree.
     * @param newComponentsList -components list.
     * @param newComponent -component.
     * */
    private void removeAll(final List<Component> newComponentsList,
                           final Component newComponent) {
        for (Component component : newComponentsList) {
            newComponent.remove(component);
        }
    }
}