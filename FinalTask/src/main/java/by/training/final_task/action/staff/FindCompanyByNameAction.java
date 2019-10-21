package by.training.final_task.action.staff;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.action.PagePagination;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CompanyProviderService;
import by.training.final_task.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindCompanyByNameAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMPANY_NAME_PARAMETER = "nameParameter";
    private static final int ROWCOUNT = 5;

    public FindCompanyByNameAction() {
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
                String companyName = getCompanyName(request);
                if (Validator.validateCompanyName(companyName)) {
                    CompanyProviderService companyService =
                            (CompanyProviderService) factory
                                    .createService(DAOEnum.COMPANYPROVIDER);
                    List<CompanyProvider> companiesList = (List<CompanyProvider>)
                            request.getAttribute("resultCompanies");
                    if (companiesList == null) {
                        int n = companyService.getAmountByCompanyName(companyName);
                        PagePagination pagination = new PagePagination(
                            companyService.getAmountByCompanyName(companyName),
                                ROWCOUNT, request.getParameter("page"));
                        request.setAttribute("amountOfPages", pagination
                                .getPagesAmount());
                        companiesList = companyService.getByCompanyName(
                                companyName, pagination.getPagesAmount(),
                                ROWCOUNT);
                        Forward forward = new Forward(
                                "/companyprovider/findcompany.html");
                        forward.getAttributes().put("resultCompanies", companiesList);
                        return forward;
                    }
                    return null;
                }
            } else {
                return new Forward("/loginpage.html", true);
            }
        }
        throw new ServiceException("forbiddenAccess");
    }

    private String getCompanyName(final HttpServletRequest request) {
        String name = request.getParameter(COMPANY_NAME_PARAMETER);
        if (name == null) {
            HttpSession session = request.getSession(false);
            name = (String) session.getAttribute(COMPANY_NAME_PARAMETER);
        }
        return name;
    }
}
