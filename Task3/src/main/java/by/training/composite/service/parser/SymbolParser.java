package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.WordComponent;
import by.training.composite.entity.SymbolComponent;
import by.training.composite.entity.PunctuationComponent;

/**
 * Symbol parser class.
 * */
public class SymbolParser extends AbstractParse {
    /**
     * Parsing method.
     * @param component -component.
     * @param part -text.
     * */
    @Override
    public void parse(final Component component, final String part) {
        if (component instanceof WordComponent) {
            WordComponent word = (WordComponent) component;
            char[] charArr = part.toCharArray();
            for (char character : charArr) {
                SymbolComponent symbol =
                        new SymbolComponent(character);
                word.add(symbol);
            }
        }
        if (component instanceof PunctuationComponent) {
            PunctuationComponent punctuation =
                    (PunctuationComponent) component;
            char[] charArr = part.toCharArray();
            for (char character : charArr) {
                SymbolComponent symbol =
                        new SymbolComponent(character);
                punctuation.add(symbol);
            }
        }
    }
}
