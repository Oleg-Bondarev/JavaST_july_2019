package by.training.final_task.action;

import by.training.final_task.entity.Role;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public abstract class AuthorizedUserAction extends Action {

    private HttpSession session;
    protected Set<Role> allowedRoles = new HashSet<>();

    public HttpSession getSession() {
        return session;
    }

    public void setSession(final HttpSession newSession) {
        session = newSession;
    }

    public Set<Role> getAllowedRoles() {
        return allowedRoles;
    }

    public void setAllowedRoles(final Set<Role> newAllowedRoles) {
        allowedRoles = newAllowedRoles;
    }
}
