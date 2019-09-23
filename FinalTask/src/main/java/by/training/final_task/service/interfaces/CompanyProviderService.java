package by.training.final_task.service.interfaces;

import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.service.ServiceException;

import java.util.List;

public interface CompanyProviderService extends Service {
    CompanyProvider getByAddress(String address) throws ServiceException;
    CompanyProvider getByPhone(int phone) throws ServiceException;
    List<by.training.final_task.entity.CompanyProvider> getAllAvailableCompany(
            int offset, int limit) throws ServiceException;
    int getAmountOfCompany() throws ServiceException;
    int getAmountOfAvailableCompany() throws ServiceException;
    int create(CompanyProvider company) throws ServiceException;
    boolean update(CompanyProvider company) throws ServiceException;
    boolean updateCompanyStatus(long id) throws ServiceException;
}
