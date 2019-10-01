package by.training.final_task.action;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import by.training.final_task.service.parser.UserFormParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class RegisterAction extends Action {

    //must be received through the service factory?
    private static final UserFormParser userParser = new UserFormParser();

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if ((session == null)
                || (session.getAttribute("authorizedUser") == null)) {
            List<String> userRegistrationParameter = new ArrayList<>();
            addRegistrationParametersToList(request, userRegistrationParameter);
            if (userRegistrationParameter.stream().anyMatch(e -> e != null)) {
                try {
                    User user = userParser.parse(this, userRegistrationParameter);
                    UserService userService = (UserService) factory
                            .createService(DAOEnum.USER);
                    //role-?
                    int userIdGenerated = userService.create(user);
                    request.setAttribute("message", "registeredSuccessfully");
                } catch (ServiceException newE) {
                    request.setAttribute("message", newE.getMessage());
                    return null;
                }
                return new Forward("/login.html");
            } else {
                return null;
            }
        } else {
            session.setAttribute("message", "alreadyLoggedIn");
            return null;
        }
    }

    //TODO what about path for user avatar?
    private void addRegistrationParametersToList(final HttpServletRequest request,
                                         final List<String> parametersList) {
        parametersList.add(request.getParameter("login"));
        parametersList.add(request.getParameter("password"));
        parametersList.add(request.getParameter("email"));
        parametersList.add(request.getParameter("firstName"));
        parametersList.add(request.getParameter("secondName"));
        parametersList.add(request.getParameter("mobilePhone"));
    }
}
