package by.training.final_task.filter;

import by.training.final_task.action.*;
import by.training.final_task.action.admin.*;
import by.training.final_task.action.staff.*;
import by.training.final_task.action.user.BuyCouponAction;
import by.training.final_task.action.user.MyPurchasePageAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provide access on a specific path to a specific action method.
 * */
public class ActionCommandClass {
    /**
     * Contain path for action method and specific method for it.
     * */
    private Map<String, Action> actionMap =
            new ConcurrentHashMap<>();
    /**
     * Constructor.
     * */
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
        actionMap.put("/user/buycoupon", new BuyCouponAction());

        actionMap.put("/user/admin/addstaffpage", new MainAction());
        actionMap.put("/user/admin/addstaff", new AddStaffAction());
        actionMap.put("/user/admin/findstaff", new FindStaffAction());
        actionMap.put("/user/admin/findusers", new FindUserAction());
        actionMap.put("/user/admin/finduserbyfirstname",
                                                new FindUserByNameAction());
        actionMap.put("/user/admin/findstaffbyfirstname",
                                              new FindStaffByFirstNameAction());
        actionMap.put("/user/admin/finduserbylogin",
                                                   new FindUserByLoginAction());

        actionMap.put("/coupon/user/findpurchases", new MainAction());
        actionMap.put("/coupon/user/mypurchases", new MyPurchasePageAction());
        actionMap.put("/coupon/moreinfo", new MoreInfoAction());
        actionMap.put("/coupon/findbycategory",
                                            new FindCouponByCategoryAction());
        actionMap.put("/coupon/findbyprice",
                                            new FindCouponByPriceRangeAction());

        actionMap.put("/companyprovider/addcompanypage", new MainAction());
        actionMap.put("/companyprovider/addcompany", new AddCompanyAction());
        actionMap.put("/companyprovider/findcompany", new FindCompanyAction());
        actionMap.put("/companyprovider/findcompanybyphone",
                                                new FindCompanyByPhoneAction());
        actionMap.put("/companyprovider/findcompanybyname",
                                                new FindCompanyByNameAction());
        actionMap.put("/companyprovider/companyblocking",
                                                   new CompanyBlockingAction());

        actionMap.put("/user/staff/addcouponpage", new AddCouponPageAction());
        actionMap.put("/user/staff/addcoupon", new AddCouponAction());
        actionMap.put("/user/staff/edditcoupon", new EditCouponAction());
        actionMap.put("/user/staff/editcouponpage", new EditCouponPageAction());
        actionMap.put("/user/staff/blockcoupon", new CouponBlockingAction());
    }

    /**
     * Getter for action map.
     * @param url way to take concrete action class.
     * @return object derived from the Action class that represents concrete
     * actions.
     * */
    public Action getAction(final String url) {
         return actionMap.get(url);
    }
}
