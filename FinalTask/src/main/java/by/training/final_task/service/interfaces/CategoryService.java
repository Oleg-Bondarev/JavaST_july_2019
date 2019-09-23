package by.training.final_task.service.interfaces;

import by.training.final_task.entity.Category;
import by.training.final_task.service.ServiceException;

import java.util.List;

public interface CategoryService extends Service {
    Category get(long id) throws ServiceException;
    List<Category> getAll() throws ServiceException;
    int getAmountOfCategory() throws ServiceException;
}
