package by.training.final_task.dao.interfases;

import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.exception.PersistentException;

import java.util.List;

public interface CompanyProviderDAO extends DAO<CompanyProvider> {
    CompanyProvider getById(long id) throws PersistentException;
    CompanyProvider getByAddress(String address) throws PersistentException;
    List<CompanyProvider> getAll() throws PersistentException;
    boolean delete(long id) throws PersistentException;
    int getAmountOfCompany() throws PersistentException;
}
