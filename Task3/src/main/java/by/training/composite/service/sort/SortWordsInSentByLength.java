package by.training.composite.service.sort;

import by.training.composite.entity.Component;
import by.training.composite.service.interfaces.SortSpecification;
import by.training.composite.service.comparator.WordsLengthComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sorting words in sentences by their length.
 * */
public class SortWordsInSentByLength implements SortSpecification<Component> {
    /**
     * return comparator.
     * */
    @Override
    public Comparator<Component> getComparator() {
        return new WordsLengthComparator();
    }
    /**
     * Sort method.
     * @param newComponent -component.
     * */
    @Override
    public void sort(final Component newComponent) {
        int count;
        int countChildren = newComponent.amountOfChildren();
        for (int i = 0; i < countChildren; ++i) {
            count = newComponent.getChild(i).amountOfChildren();
            for (int j = 0; j < count; ++j) {
                    sortingWords(newComponent.getChild(i).getChild(j));
            }
        }
    }
    /**
     * Sorting words.
     * @param newComponent -component.
     * */
    private void sortingWords(final Component newComponent) {
        List<Component> componentsList = new ArrayList<>();
        int countChildren = newComponent.amountOfChildren();
        for (int i = 0; i < countChildren; ++i) {
            componentsList.add(newComponent.getChild(i));
        }
        componentsList.sort(this.getComparator());
        removeAll(componentsList, newComponent);
        addAll(componentsList, newComponent);
    }
    /**
     * Method remove all components from tree.
     * @param newComponentsList -component list.
     * @param newComponent -component.
     * */
    private void removeAll(final List<Component> newComponentsList,
                           final Component newComponent) {
        for (Component component : newComponentsList) {
            newComponent.remove(component);
        }
    }
    /**
     * Method add new component in tree.
     * @param newComponentsList -component list.
     * @param newComponent -component.
     * */
    private void addAll(final List<Component> newComponentsList,
                        final Component newComponent) {
        for (Component component : newComponentsList) {
            newComponent.add(component);
        }
    }
}
