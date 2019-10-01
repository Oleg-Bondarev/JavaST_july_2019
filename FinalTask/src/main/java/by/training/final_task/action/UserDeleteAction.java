package by.training.final_task.action;

import by.training.final_task.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//blocking(not delete from data base)
public class UserDeleteAction extends AuthorizedUserAction {
    @Override
    public Forward executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
