package by.training.final_task.service.parser;

import by.training.final_task.action.Action;
import by.training.final_task.entity.Coupon;
import by.training.final_task.service.ServiceException;

import java.util.List;

public class CouponFormParser extends FormParser<Coupon> {
    @Override
    public Coupon parse(final Action newAction,
                        final List<String> newParameters)
            throws ServiceException {
        return null;
    }
}
