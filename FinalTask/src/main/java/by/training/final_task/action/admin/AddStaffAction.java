package by.training.final_task.action.admin;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.action.RegisterAction;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import by.training.final_task.service.parser.InvalidFormDataException;
import by.training.final_task.service.parser.UserFormParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent adding stuff action to a system.
 * */
public class AddStaffAction extends AuthorizedUserAction {
    /**
     * Class logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * For parsing input parameters from registration form.
     * */
    private final UserFormParser userFormParser = new UserFormParser();
    /**
     * Url.
     * */
    private static final String URL_PATH = "/user/admin/addstaffpage.html";
    /**
     * Set roles that can execute this request.
     * */
    public AddStaffAction() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user1 = (User) session.getAttribute("authorizedUser");
            if ((user1 != null) && allowedRoles.contains(user1.getRole())) {
                List<String> userParameters = new ArrayList<>();
                addUserParameters(request, userParameters);
                try {
                    User user = userFormParser.parse(this,
                            userParameters);
                    user.setRole(Role.STAFF);
                    user.setPathToAvatar("img/staff/user5.jpg");
                    UserService userService = (UserService) factory
                            .createService(DAOEnum.USER);
                    int userIdGenerated = userService.create(user);
                    user.setId(userIdGenerated);
                    return executeForward(URL_PATH, "successMessage",
                            "staffAdded");
                } catch (InvalidFormDataException | ServiceException newE) {
                    return executeForward(URL_PATH, "message",
                            newE.getMessage());
                }
            }
            return new Forward("/login.html", true);
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} and failed",
                request.getRemoteAddr(), request.getRequestURI());
        return new Forward("/login.html", true);
    }

    /**
     * Call method that get user parameters from request and add them to list.
     * @param request on action.
     * @param userParameters list to fill user parameters.
     * */
    private void addUserParameters(final HttpServletRequest request,
                                   final List<String> userParameters) {
        RegisterAction.addUserParametersToList(request, userParameters);
    }
}
