package by.training.final_task.dao.interfases;

import by.training.final_task.entity.Category;
import by.training.final_task.exception.PersistentException;

import java.util.List;

public interface CategoryDAO {
    int GetAmountOfCategory() throws PersistentException;
}
