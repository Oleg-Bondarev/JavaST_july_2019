package by.training.composite.dao;

import by.training.composite.dao.exceptions.IncorrectIndexException;

/**
 * Text dao interface.
 * */
public interface TextDAO {
    /**
     * Add text.
     * @param newText -new text.
     * */
    void addText(String newText);
    /**
     * Get concrete text.
     * @param index -index.
     * @return text.
     * @throws IncorrectIndexException -if incorrect index.
     * */
    String getText(int index) throws IncorrectIndexException;
}
