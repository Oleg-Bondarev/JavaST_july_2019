package by.training.composite.service.sort;

import by.training.composite.entity.Component;
import by.training.composite.entity.SentenceComponent;
import by.training.composite.entity.WordComponent;
import by.training.composite.service.interfaces.SortSpecification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sort tokens in the text in descending order of the number of occurrences
 * of a given character, and in case of equality - alphabetically.
 * */
public class SortLexemesByCharacterEntry
        implements SortSpecification<Component> {
    /**
     * Entering character.
     * */
    private char character;
    /**
     * Constructor.
     * @param newCharacter -new character for sorting.
     * */
    public SortLexemesByCharacterEntry(final char newCharacter) {
        this.character = newCharacter;
    }
    /**
     * @return comparator.
     * */
    @Override
    public Comparator<Component> getComparator() {
        return null;
    }

    /**
     * Sort method.
     * @param newComponent -component for sorting.
     * */
    public void sort(final Component newComponent) {
        if (newComponent instanceof SentenceComponent) {
            List<Component> lexemesList = readChildrens(newComponent);
            lexemesList.forEach(newComponent::remove);
            lexemesList.sort(Comparator.comparing(
                    lex -> countOfCharacters((Component) lex)).reversed()
                    .thenComparing(lex -> ((Component) lex).compose()));
            lexemesList.forEach(newComponent::add);
        } else {
            int countComponents = newComponent.amountOfChildren();
            for (int i = 0; i < countComponents; ++i) {
                sort(newComponent.getChild(i));
            }
        }
    }
    /**
     * Reading children.
     * @param component -component.
     * @return list.
     * */
    private List<Component> readChildrens(final Component component) {
        int countOfComponents = component.amountOfChildren();
        List<Component> childrenList = new ArrayList<>();
        for (int i = 0; i < countOfComponents; ++i) {
            childrenList.add(component.getChild(i));
        }
        return childrenList;
    }
    /**
     * Count of characters.
     * @param lexeme -lexeme.
     * @return count.
     * */
    private int countOfCharacters(final Component lexeme) {
        List<Component> words = readChildrens(lexeme).stream()
                .filter(comp -> comp instanceof WordComponent)
                .collect(Collectors.toList());
        int count = 0;
        for (Component word : words) {
            List<Component> letters = readChildrens(word);
            final String stringRepresent = String.valueOf(character);
            count += letters.stream()
                    .filter(letter -> letter.compose().equals(stringRepresent))
                    .count();
        }
        return count;
    }
}
