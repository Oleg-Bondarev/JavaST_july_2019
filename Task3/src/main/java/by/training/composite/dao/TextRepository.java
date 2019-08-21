package by.training.composite.dao;

import by.training.composite.dao.exceptions.IncorrectIndexException;
import by.training.composite.entity.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Text repository class.
 * */
public final class TextRepository implements TextDAO {
    /**
     * Instance.
     * */
    private static final TextRepository INSTANCE = new TextRepository();
    /**
     * List of texts.
     * */
    private List<String> textList = new ArrayList<>();
    /**
     * Text component.
     * */
    private Component textComponent;
    /**
     * Constructor.
     * */
    private TextRepository() { }
    /**
     * @return class instance.
     * */
    public static TextRepository getInstance() {
        return INSTANCE;
    }
    /**
     * @param newText -new text.
     * */
    @Override
    public void addText(final String newText) {
        if ((newText != null) && !newText.isEmpty()) {
            textList.add(newText);
        }
    }
    /**
     * @param index -text index.
     * @return text.
     * @throws IncorrectIndexException -if incorrect index.
     * */
    @Override
    public String getText(final int index) throws IncorrectIndexException {
        if (index < 0) {
            throw new IncorrectIndexException("Incorrect index of text: "
                    + index);
        }
        return textList.get(index);
    }
    /**
     * Setter.
     * @param component -text component.
     * */
    public void setTextComponent(final Component component) {
        this.textComponent = component;
    }
    /**
     * Getter.
     * @return text component.
     * */
    public Component getTextComponent() {
        return textComponent;
    }
}
