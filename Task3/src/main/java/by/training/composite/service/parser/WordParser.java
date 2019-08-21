package by.training.composite.service.parser;

import by.training.composite.entity.Component;
import by.training.composite.entity.LexemeComponent;
import by.training.composite.entity.PunctuationComponent;
import by.training.composite.entity.WordComponent;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represent parsing word.
 * */
public class WordParser extends AbstractParse {
    /**
     * Regex for parsing lexeme.
     * */
    private static final String REGEX = "[,:;'\"!.?]";
    /**
     * Pattern.
     * */
    private Pattern pattern = Pattern.compile(REGEX);
    /**
     * Parse method.
     * @param component -component.
     * @param part -text.
     * */
    @Override
    public void parse(final Component component, final String part) {
        Matcher matcher = pattern.matcher(part);
        List<Integer> punctuationIndexes = new LinkedList<>();
        while (matcher.find()) {
            punctuationIndexes.add(matcher.start());
        }
        if (component instanceof LexemeComponent) {
            LexemeComponent lexeme = (LexemeComponent) component;
            if (punctuationIndexes.isEmpty()) {
                WordComponent word = new WordComponent();
                lexeme.add(word);
                chain(word, part);
                return;
            }
            int previousInd = 0;
            int size = punctuationIndexes.size();
            for (int i = 0; i < size; i++) {
                int punctuationInd = punctuationIndexes.get(i);
                if (part.substring(previousInd, punctuationInd).isEmpty()) {
                    previousInd = punctuationInd + 1;
                    if (i == punctuationIndexes.size() - 1) {
                        if (!part.substring(previousInd).isEmpty()) {
                            addMidPunctuation(lexeme, part, punctuationInd);
                            addLastWord(lexeme, part, previousInd);
                        } else {
                            addLastPunctuation(lexeme, part, punctuationInd);
                        }
                    } else {
                        addMidPunctuation(lexeme, part, punctuationInd);
                    }
                } else {
                    WordComponent word = new WordComponent();
                    lexeme.add(word);
                    chain(word, part.substring(previousInd, punctuationInd));
                    previousInd = punctuationInd + 1;
                    if (i == punctuationIndexes.size() - 1) {
                        if (!part.substring(previousInd).isEmpty()) {
                            addMidPunctuation(lexeme, part, punctuationInd);
                            addLastWord(lexeme, part, previousInd);
                        } else {
                            addLastPunctuation(lexeme, part, punctuationInd);
                        }
                    } else {
                        addMidPunctuation(lexeme, part, punctuationInd);
                    }
                }
            }
        }
    }
    /**
     * @param lexeme -component.
     * @param part -text.
     * @param previousInd -index.
     * */
    private void addLastWord(final LexemeComponent lexeme,
                             final String part, final int previousInd) {
        WordComponent word = new WordComponent();
        lexeme.add(word);
        chain(word, part.substring(previousInd));
    }
    /**
     * @param lexeme -component.
     * @param part -text.
     * @param punctuationInd -index.
     * */
    private void addMidPunctuation(final LexemeComponent lexeme,
                                   final String part,
                                   final int punctuationInd) {
        PunctuationComponent punctuation = new PunctuationComponent();
        lexeme.add(punctuation);
        chain(punctuation, Character.toString(part.charAt(punctuationInd)));
    }
    /**
     * @param lexeme -component.
     * @param part -text.
     * @param punctuationInd -index.
     * */
    private void addLastPunctuation(final LexemeComponent lexeme,
                                    final String part,
                                    final int punctuationInd) {
        PunctuationComponent punctuation = new PunctuationComponent();
        lexeme.add(punctuation);
        chain(punctuation, part.substring(punctuationInd));
    }
}
