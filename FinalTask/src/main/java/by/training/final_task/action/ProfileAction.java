package by.training.final_task.action;

import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileAction extends AuthorizedUserAction {

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if (user != null) {
                request.setAttribute("user", user);
                return null;
            }
            return new Forward("/login.html", true);
        }
        return new Forward("/", true);
    }
}
