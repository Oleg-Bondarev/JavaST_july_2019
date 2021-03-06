package by.training.final_task.action;

import by.training.final_task.controller.ControllerException;
import by.training.final_task.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionManager {

    Action.Forward execute(Action action, HttpServletRequest request,
                           HttpServletResponse response)
            throws ControllerException, ServiceException;

    void close() throws ServiceException;
}
