package by.training.final_task.action.staff;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.action.PagePagination;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CompanyProviderService;
import by.training.final_task.service.validator.PhoneValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class FindCompanyByPhoneAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PHONE_PARAMETER = "phoneParameter";
    private static final int ROWS_IN_PAGE = 5;

    FindCompanyByPhoneAction() {
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
                String phone = getCompanyPhone(request);
                if (PhoneValidator.validate(phone)) {
                    CompanyProviderService companyService =
                            (CompanyProviderService) factory
                                    .createService(DAOEnum.COMPANYPROVIDER);
                    int mobilePhone = Integer.parseInt(phone);
                    List<CompanyProvider> list = new ArrayList<>();
                    CompanyProvider company = companyService.getByPhone(mobilePhone);
                    list.add(company);
                    Forward forward = new Forward(
                            "/companyprovider/findcompany.html");
                    forward.getAttributes().put("resultCompanies", list);
                    return forward;
                } else {
                    request.setAttribute("message",
                            "incorrectPhoneParameter");
                    return null;
                }
            }
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} anf failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }

    private String getCompanyPhone(final HttpServletRequest request) {
        String phone = request.getParameter(PHONE_PARAMETER);
        if (phone == null) {
            HttpSession session = request.getSession(false);
            phone = (String) session.getAttribute(PHONE_PARAMETER);
        }
        return phone;
    }
}
