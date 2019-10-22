package by.training.final_task.service.interfaces;

import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.service.ServiceException;

import java.util.List;

public interface CompanyProviderService extends Service {
    CompanyProvider get(int id) throws ServiceException;
    CompanyProvider getByPhone(int phone) throws ServiceException;
    List<CompanyProvider> getAllAvailableCompany(int offset, int limit)
        throws ServiceException;
    List<CompanyProvider> getAvailableCompanyList() throws ServiceException;
    List<CompanyProvider> getByCompanyName(String name, int offset, int limit)
        throws ServiceException;
    int getAmountOfCompany() throws ServiceException;
    int getAmountOfAvailableCompany() throws ServiceException;
    int getAmountByCompanyName(String name) throws ServiceException;
    int create(CompanyProvider company) throws ServiceException;
    boolean update(CompanyProvider company) throws ServiceException;
    boolean updateCompanyStatus(long id) throws ServiceException;
}
