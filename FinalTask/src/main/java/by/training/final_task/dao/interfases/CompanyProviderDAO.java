package by.training.final_task.dao.interfases;

import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.exception.PersistentException;

import java.util.List;

public interface CompanyProviderDAO extends DAO<CompanyProvider> {
    CompanyProvider getByAddress(String address) throws PersistentException;
    CompanyProvider getByPhone(int phone) throws PersistentException;
    List<CompanyProvider> getAllAvailableCompany(int offset, int limit)
            throws PersistentException;
    int getAmountOfCompany() throws PersistentException;
    int getAmountOfAvailableCompany() throws PersistentException;
}
