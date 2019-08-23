package by.training.composite.dao;

/**
 * Console printer.
 * */
public final class ConsolePrinter {
    /**
     * Constructor.
     * */
    private ConsolePrinter() { }
    /**
     * @param message -message for writing in console.
     * */
    public static void print(final Object message) {
        System.out.println(message);
    }
}
