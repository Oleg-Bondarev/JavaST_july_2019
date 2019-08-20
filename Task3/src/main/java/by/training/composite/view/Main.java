package by.training.composite.view;

import by.training.composite.dao.DataInputReader;
import by.training.composite.dao.exceptions.FileReaderException;
import by.training.composite.entity.Lexeme;
import by.training.composite.entity.Text;
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

        TextParser textParser = TextParser.getInstance();
        ParagraphParser paragraphParser = ParagraphParser.getInstance();
        SentenceParser sentenceParser = SentenceParser.getInstance();
        LexemeParser lexemeParser = LexemeParser.getInstance();
        WordParser wordParser = WordParser.getInstance();

        textParser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        Text text = new Text();

        textParser.parse(text, str);
    }

}
