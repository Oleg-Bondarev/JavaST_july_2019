package by.training.final_task.filter;

import by.training.final_task.action.*;
import by.training.final_task.action.admin.*;
import by.training.final_task.action.staff.*;

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
        actionMap.put("/coupons", new CouponAction());
        actionMap.put("/user/profile", new ProfileAction());

        actionMap.put("/user/userblocking", new UserBlockingAction());
        actionMap.put("/user/usereditpage", new UserEditPageAction());
        actionMap.put("/user/useredit", new UserEditAction());
        actionMap.put("/user/userblock", new UserBlockingAction());

        actionMap.put("/user/admin/addstaffpage", new MainAction());
        actionMap.put("/user/admin/addstaff", new AddStaffAction());
        actionMap.put("/user/admin/findstaff", new FindStaffAction());
        actionMap.put("/user/admin/findusers", new FindUserAction());
        actionMap.put("/user/admin/finduserbyfirstname", new FindUserByNameAction());//
        actionMap.put("/user/admin/findstaffbyfirstname", new FindStaffByFirstNameAction());

        actionMap.put("/coupon/user/findpurchases", new MainAction());
        actionMap.put("/coupon/moreinfo", new MoreInfoAction());

        actionMap.put("/companyprovider/addcompanypage", new MainAction());
        actionMap.put("/companyprovider/addcompany", new AddCompanyAction());
        actionMap.put("/companyprovider/findcompany", new FindCompanyAction());
        actionMap.put("/companyprovider/findcompanybyphone", new FindCompanyByPhoneAction());
        actionMap.put("/companyprovider/findcompanybyname", new FindCompanyByNameAction());
        actionMap.put("/companyprovider/companyblocking", new CompanyBlockingAction());

        actionMap.put("/user/staff/addcouponpage", new AddCouponPageAction());
        actionMap.put("/user/staff/addcoupon", new AddCouponAction());
        actionMap.put("/user/staff/edditcoupon", new EditCouponAction());
        actionMap.put("/user/staff/editcouponpage", new EditCouponPageAction());
    }

    public Action getAction(final String url) {
         return actionMap.get(url);
    }
}
