package by.training.final_task.filter;

import by.training.final_task.action.*;
import by.training.final_task.action.admin.FindStaffAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionCommandClass {
    private Map<String, Action> actionMap =
            new ConcurrentHashMap<>();

    ActionCommandClass() {
        actionMap.put("/", new MainAction());
        actionMap.put("/homepage", new MainAction());
        actionMap.put("/loginpage", new MainAction());
        actionMap.put("/login", new LoginAction());
        actionMap.put("/logout", new LogoutAction());
        actionMap.put("/register", new RegisterAction());
        actionMap.put("/registration", new MainAction());
        actionMap.put("/coupons", new MainAction());
        actionMap.put("/user/profile", new ProfileAction());
        actionMap.put("/user/userblocking", new UserBlockingAction());
        actionMap.put("/user/usereditpage", new UserEditPageAction());
        actionMap.put("/user/useredit", new UserEditAction());
        actionMap.put("/user/admin/addadminpage", new MainAction());
        actionMap.put("/user/admin/findadmin", new FindStaffAction());
        actionMap.put("/coupon/user/mypurchases", new MainAction());
    }

    public Action getAction(final String url) {
         return actionMap.get(url);
    }
}
