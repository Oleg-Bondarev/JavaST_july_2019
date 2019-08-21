package by.training.composite.view;

import by.training.composite.dao.DataInputReader;
import by.training.composite.dao.exceptions.FileReaderException;
import by.training.composite.entity.SymbolComponent;
import by.training.composite.entity.TextComponent;
import by.training.composite.service.parser.*;

public class Main {
    public static void main(String[] args) {
        String path = "data\\input.txt";
        DataInputReader dataInputReader = new DataInputReader();
        String str = null;

        try {
            str = dataInputReader.readInformationFromFile(path);
        } catch (FileReaderException e) {
            e.printStackTrace();
        }
        System.out.println(str);

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
        TextComponent text = new TextComponent();

        textParser.parse(text, str);
    }

}
