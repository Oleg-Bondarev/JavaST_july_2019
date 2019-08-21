package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.WordComponent;
import by.training.composite.entity.NewLineSymbol;
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
            String[] symbolsArr = part.split("");
            for (String symbolStr : symbolsArr) {
                if (symbolStr.isEmpty()) {
                    NewLineSymbol symbol = new NewLineSymbol("\r\n".getBytes());
                    word.add(symbol);
                } else {
                    SymbolComponent symbol =
                            new SymbolComponent(symbolStr.toCharArray()[0]);
                    word.add(symbol);
                }
            }
        }
        if (component instanceof PunctuationComponent) {
            PunctuationComponent punctuationMark =
                    (PunctuationComponent) component;
            String[] symbolsArr = part.split("");
            for (String symbolStr : symbolsArr) {
                if (symbolStr.isEmpty()) {
                    NewLineSymbol symbol = new NewLineSymbol("\r\n".getBytes());
                    punctuationMark.add(symbol);
                } else {
                    SymbolComponent symbol =
                            new SymbolComponent(symbolStr.toCharArray()[0]);
                    punctuationMark.add(symbol);
                }
            }
        }
    }
}
