package by.training.final_task.action.user;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyCouponAction extends AuthorizedUserAction {
    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        return null;
    }
}
