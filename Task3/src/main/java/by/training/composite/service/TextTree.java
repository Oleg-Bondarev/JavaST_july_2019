package by.training.composite.service;

import by.training.composite.entity.Component;
import by.training.composite.entity.TextComponent;
import by.training.composite.service.parser.*;

/**
 * Represent creating text tree.
 * */
public class TextTree {
    /**
     * Creating tree.
     * @param text -text in string.
     * @return component.
     * */
    public Component createTree(final String text) {
        TextComponent textComponent = new TextComponent();
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();

        textParser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        textParser.parse(textComponent, text);
        return textComponent;
    }
}
