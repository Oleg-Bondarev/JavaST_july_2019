package by.training.final_task.dao.interfases;

import by.training.final_task.entity.Reviews;
import by.training.final_task.exception.PersistentException;

import java.util.List;

public interface ReviewsDAO extends DAO<Reviews> {
    Reviews getById(long id) throws PersistentException;
    List<Reviews> getAll(int offset, int limit) throws PersistentException;
    List<Reviews> getAllReviewsCurrentUser(long userId, int offset, int limit)
        throws PersistentException;
    int getAllCount() throws PersistentException;
    int getCountReviewsCurrentUser(long userId, int offset, int limit)
        throws PersistentException;
    boolean delete(long id) throws PersistentException;
}
