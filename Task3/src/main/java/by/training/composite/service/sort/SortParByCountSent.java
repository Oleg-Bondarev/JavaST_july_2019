package by.training.composite.service.sort;

import by.training.composite.entity.Component;
import by.training.composite.service.interfaces.SortSpecification;
import by.training.composite.service.comparator.AmountOfSentencesInParagraphComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sorting paragraphs by count sentences.
 * */
public class SortParByCountSent implements SortSpecification<Component> {
    /**
     * @return comparator.
     * */
    @Override
    public Comparator<Component> getComparator() {
        return new AmountOfSentencesInParagraphComparator();
    }
    /**
     * Sort method.
     * @param newComponent -component.
     * */
    @Override
    public void sort(final Component newComponent) {
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
