package by.training.final_task.action;

import by.training.final_task.service.interfaces.ServiceFactory;

public class ActionManagerFactory {
    public static ActionManager getManager(final ServiceFactory factory) {
        return new ActionManagerImpl(factory);
    }
}
