package by.training.final_task.action.staff;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CompanyProviderService;
import by.training.final_task.service.parser.CompanyFormParser;
import by.training.final_task.service.parser.InvalidFormDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddCompanyAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final CompanyFormParser companyParser =
            new CompanyFormParser();

    public AddCompanyAction() {
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
                List<String> companyParameters = new ArrayList<>();
                addCompanyParameters(request, companyParameters);
                try {
                    CompanyProvider company = companyParser.parse(this,
                            companyParameters);
                    CompanyProviderService companyService =
                            (CompanyProviderService) factory
                                    .createService(DAOEnum.COMPANYPROVIDER);
                    int companyIdGenerated = companyService.create(company);
                    company.setId(companyIdGenerated);

                    return executeForward(
                            "/companyprovider/addcompanypage.html",
                            "successMessage", "companyAdded");
                } catch (InvalidFormDataException | ServiceException newE) {
                    return executeForward(
                            "/companyprovider/addcompanypage.html",
                            "message", newE.getMessage());
                }
            }
            return new Forward("/loginpage.html", true);
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} and failed",
                request.getRemoteAddr(), request.getRequestURI());
        return new Forward("/loginpage.html", true);
    }

    private void addCompanyParameters(final HttpServletRequest request,
                                      final List<String> companyParameters) {
        companyParameters.add(request.getParameter("companyName"));
        companyParameters.add(request.getParameter("companyAddress"));
        companyParameters.add(request.getParameter("companyPhone"));
    }
}
