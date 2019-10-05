package by.training.final_task.action;

import by.training.final_task.dao.PersistentException;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.implimentation.ServiceFactoryImpl;
import by.training.final_task.service.interfaces.ServiceFactory;
import by.training.final_task.service.interfaces.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//blocking(not delete from data base)
public class UserBlockingAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger();

    public UserBlockingAction() {
        this.getAllowedRoles().add(Role.ADMIN);
        this.getAllowedRoles().add(Role.USER);
    }


    //TODO fix pass for jsp pages
    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if ((user != null) && this.getAllowedRoles().contains(
                    user.getRole())) {
                long userId = Long.parseLong(request.getParameter("userToDelete"));
                UserService userService = (UserService) factory.createService(
                        DAOEnum.USER);
                userService.updateUserState(userId);

                if (user.getId() == userId) {
                    return new Forward("/logout.html", true);
                } else {
                    session.setAttribute("message", "userDeleted");
                    return new Forward(request.getHeader("referer"), true);
                }
            } else {
                throw new ServiceException("forbiddenAccess");
            }
        }
        LOGGER.log(Level.INFO, "{} attempted to access {} and failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }
}
