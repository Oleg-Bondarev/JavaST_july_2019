package by.training.composite.entity;

/**
 * New line symbol.
 * */
public class NewLineSymbol implements Component {
    /**
     * Array of symbol bytes.
     * */
    private byte[] symbol;
    /**
     * Constructor.
     * @param newSymbol -array of byte symbols.
     * */
    public NewLineSymbol(final byte[] newSymbol) {
        this.symbol = newSymbol;
    }
    /**
     * Gathered strings.
     * */
    @Override
    public String compose() {
        return new String(symbol).intern();
    }
}
