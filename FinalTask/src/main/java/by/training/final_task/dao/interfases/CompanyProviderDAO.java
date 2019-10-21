package by.training.final_task.dao.interfases;

import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.dao.PersistentException;

import java.util.List;

public interface CompanyProviderDAO extends DAO<CompanyProvider> {
    CompanyProvider getByPhone(int phone) throws PersistentException;
    List<CompanyProvider> getByCompanyName(String companyName, int offset,
                                          int limit)
            throws PersistentException;
    List<CompanyProvider> getAllAvailableCompany(int offset, int limit)
            throws PersistentException;
    int getAmountByName(String name) throws PersistentException;
    int getAmountOfCompany() throws PersistentException;
    int getAmountOfAvailableCompany() throws PersistentException;
    boolean updateCompanyStatus(final long id) throws PersistentException;
}
