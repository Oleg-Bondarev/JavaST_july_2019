package by.training.final_task.action.admin;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.action.PagePagination;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindUserAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int ROWCOUNT = 5;

    public FindUserAction() {
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
                UserService companyService = (UserService)
                        factory.createService(DAOEnum.USER);
                List<User> usersList = (List<User>)
                        request.getAttribute("resultUsers");
                if (usersList == null) {
                    PagePagination pagination = new PagePagination(
                            companyService.getAmountOfAllUsersByRole(Role.USER),
                            ROWCOUNT, request.getParameter("page"));
                    request.setAttribute("amountOfPages", pagination
                            .getPagesAmount());
                    usersList = companyService.getAllUsersByRole(Role.USER,
                            pagination.getPageOffset(), ROWCOUNT);
                    request.setAttribute("resultUsers", usersList);
                    request.setAttribute("paginationURL",
                            "/user/admin/findusers.html");
                }
                return null;
            } else {
                return new Forward("/loginpage.html", true);
            }
        }
        throw new ServiceException("forbiddenAccess");
    }
}
