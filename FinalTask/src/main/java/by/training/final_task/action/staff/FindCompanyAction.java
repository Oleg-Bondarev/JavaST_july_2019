package by.training.final_task.action.staff;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.action.PagePagination;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CategoryService;
import by.training.final_task.service.interfaces.CompanyProviderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindCompanyAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int ROWCOUNT = 5;

    public FindCompanyAction() {
        allowedRoles.add(Role.STAFF);
    }

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if ((user != null) && allowedRoles.contains(user.getRole())) {
                CompanyProviderService companyService = (CompanyProviderService)
                        factory.createService(DAOEnum.COMPANYPROVIDER);
                List<CompanyProvider> companiesList = (List<CompanyProvider>)
                        request.getAttribute("resultCompanies");
                if (companiesList == null) {
                    PagePagination pagination = new PagePagination(
                            companyService.getAmountOfAvailableCompany(),
                            ROWCOUNT, request.getParameter("page"));
                    request.setAttribute("amountOfPages", pagination
                            .getPagesAmount());
                    companiesList = companyService.getAllAvailableCompany(
                            pagination.getPageOffset(), ROWCOUNT);
                    request.setAttribute("resultCompanies", companiesList);
                    request.setAttribute("paginationURL",
                            "/companyprovider/findcompany.html");
                }
                return null;
            } else {
                return new Forward("/loginpage.html", true);
            }
        }
        throw new ServiceException("forbiddenAccess");
    }
}
