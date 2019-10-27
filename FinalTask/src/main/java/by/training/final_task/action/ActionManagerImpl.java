package by.training.final_task.action;

import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionManagerImpl implements ActionManager {
    private ServiceFactory factory;

    public ActionManagerImpl(final ServiceFactory newServiceFactory) {
        factory = newServiceFactory;
    }

    @Override
    public Action.Forward execute(final Action action,
                                  final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        action.setFactory(factory);
        return action.executeRequest(request, response);
    }

    @Override
    public void close() throws ServiceException {
        factory.close();
    }
}
