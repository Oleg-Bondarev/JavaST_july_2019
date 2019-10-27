package by.training.final_task.action;

import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public abstract class Action {

    private User authorizedUser;
    private String name;
    public ServiceFactory factory;



    public User getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(final User newAuthorizedUser) {
        authorizedUser = newAuthorizedUser;
    }

    public String getName() {
        return name;
    }

    public void setName(final String newName) {
        name = newName;
    }

    public void setFactory(final ServiceFactory newFactory) {
        factory = newFactory;
    }

    public abstract Forward executeRequest(final HttpServletRequest request,
                                    final HttpServletResponse response)
            throws ServiceException;

    protected Forward executeForward(final String newUrl, final String newParam,
                                     final String newMsg) {
        Forward forward = new Forward(newUrl);
        forward.getAttributes().put(newParam, newMsg);
        return forward;
    }

    public static class Forward {
        private String forward;
        private boolean redirect;
        private Map<String, Object> attributes = new HashMap<>();

        protected ServiceFactory factory;

        public  Forward(final String newForward, final boolean newRedirect) {
            forward = newForward;
            redirect = newRedirect;
        }

        public Forward(final String newForward) {
            this(newForward, true);
        }

        public String getForward() {
            return forward;
        }

        public void setForward(final String newForward) {
            forward = newForward;
        }

        public boolean isRedirect() {
            return redirect;
        }

        public void setRedirect(final boolean newRedirect) {
            redirect = newRedirect;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }
    }
}
