package by.training.final_task.dao.interfases;

import by.training.final_task.entity.Category;
import by.training.final_task.exception.PersistentException;

import java.util.List;

public interface CategoryDAO {
    Category getById(int id) throws PersistentException;
    List<Category> getAll() throws PersistentException;
    boolean delete(int id) throws PersistentException;
    int GetAmountOfCategory() throws PersistentException;
}
