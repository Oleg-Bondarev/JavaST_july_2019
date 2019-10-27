package by.training.final_task.action.staff;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CategoryService;
import by.training.final_task.service.interfaces.CompanyProviderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddCouponPageAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();

    public AddCouponPageAction() {
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
                CategoryService categoryService = (CategoryService) factory
                        .createService(DAOEnum.CATEGORY);
                CompanyProviderService companyProviderService =
                        (CompanyProviderService) factory
                                .createService(DAOEnum.COMPANYPROVIDER);
                List<Category> categoryList = categoryService.getAll();
                List<CompanyProvider> providersList = companyProviderService
                        .getAvailableCompanyList();
                request.setAttribute("categoryList", categoryList);
                request.setAttribute("providersList", providersList);
                return null;
            }
            LOGGER.log(Level.INFO, "{} - attempted to access {} and"
                            + " stopped due to not enough privileges",
                    request.getRemoteAddr(), request.getRequestURI());
        }
        throw new ServiceException("forbiddenAccess");
    }
}
