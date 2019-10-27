package by.training.final_task.action.admin;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class FindUserByLoginAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();

    public FindUserByLoginAction() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if ((user != null) && allowedRoles.contains(user.getRole())) {
                String userLogin = request.getParameter("userLogin");
                List<User> userList = new ArrayList<>();
                Forward forward = new Forward(
                        "/user/admin/findusers.html");
                if (!userLogin.isEmpty()) {
                    if (validator.validateUserLogin(userLogin)) {
                        UserService userService = (UserService) factory
                                .createService(DAOEnum.USER);
                        User findUser = userService.getByLogin(userLogin);
                        userList.add(findUser);
                    }
                }
                forward.getAttributes().put("amountOfPages", 1);
                forward.getAttributes().put("resultUsers", userList);
                return forward;
            }
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} anf failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }
}
