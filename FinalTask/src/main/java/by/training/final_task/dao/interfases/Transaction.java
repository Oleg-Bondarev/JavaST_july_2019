package by.training.final_task.dao.interfases;

import by.training.final_task.exception.PersistentException;

public interface Transaction {
    <T extends DAO<?>> T createDAO(Class<T> key) throws PersistentException;
    void commitTransaction() throws PersistentException;
    void rollbackTransaction() throws PersistentException;
}
