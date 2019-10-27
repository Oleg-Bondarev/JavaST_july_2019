package by.training.final_task.service.parser;

import by.training.final_task.action.Action;
import by.training.final_task.entity.Coupon;
import by.training.final_task.service.interfaces.CategoryService;
import by.training.final_task.service.validator.CouponParametersValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CouponFormParser extends FormParser<Coupon> {

    @Override
    public Coupon parse(final Action newAction,
                        final List<String> newParameters)
            throws InvalidFormDataException {
        if ((newParameters != null) && !newParameters.contains(null)
                && !newParameters.contains("")) {
            CouponParametersValidator validator =
                    new CouponParametersValidator();
            Map<String, Boolean> validationMap =
                    validator.validate(newParameters);
            for (Map.Entry<String, Boolean> entry : validationMap.entrySet()) {
                if (entry.getValue()) {
                    throw new InvalidFormDataException(entry.getKey());
                }
            }
            String couponName = newParameters.get(validator
                    .getCouponNameIndex());
            String couponDescription = newParameters.get(validator
                    .getCouponDescriptionIndex());
            String stringPrice = newParameters.get(validator
                    .getCouponPriceIndex());
            BigDecimal couponPrice = new BigDecimal(stringPrice);
            String holdingAddress = newParameters.get(validator
                    .getHoldingAddressIndex());
            LocalDate registrationDate = LocalDate.now();

            return new Coupon(0, couponName, null,
                    couponDescription, couponPrice, registrationDate,
                    holdingAddress, null, null,
                    false);
        }
        throw new InvalidFormDataException("fillAllFields");
    }
}
