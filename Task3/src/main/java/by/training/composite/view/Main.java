package by.training.composite.view;

import by.training.composite.controller.Controller;

/**
 * Main class.
 * */
public final class Main {
    /**
     * Constructor.
     * */
    private Main() { }
    /**
     * Main method.
     * @param args -args.
     * */
    public static void main(final String[] args) {
        /*String path = "data\\input.txt";
        DataInputReader dataInputReader = new DataInputReader();
        String str = null;

        try {
            str = dataInputReader.readInformationFromFile(path);
        } catch (FileReaderException e) {
            e.printStackTrace();
        }*/
        //System.out.println(str);


        /*TextComponent text = new TextComponent();

        textParser.parse(text, str);
        String st = text.compose();
        System.out.println(st);*/

        Controller controller = new Controller();
        controller.execute("LOAD_FROM_FILE_TEXT");
        controller.execute("PRINT_TEXT");
    }
}
