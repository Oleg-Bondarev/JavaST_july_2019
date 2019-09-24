package by.training.final_task.dao.interfases;

import by.training.final_task.dao.PersistentException;

public interface TransactionFactory {
    Transaction createTransaction() throws PersistentException;
    void closeTransaction() throws PersistentException;
}
