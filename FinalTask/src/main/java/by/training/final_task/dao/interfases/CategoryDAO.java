package by.training.final_task.dao.interfases;

import by.training.final_task.entity.Category;
import by.training.final_task.exception.PersistentException;

import java.util.List;

public interface CategoryDAO {
    Category get() throws PersistentException;
    Category get(long id) throws PersistentException;
    List<Category> getAll() throws PersistentException;
    int getAmountOfCategory() throws PersistentException;
}
