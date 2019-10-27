package by.training.final_task.action;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Coupon;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CouponService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * Represent search coupons by price range.
 */
public class FindCouponByPriceRangeAction extends AuthorizedUserAction {
    /**
     * Class logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Count objects in the page for pagination.
     */
    private static final int ROWCOUNT = 10;
    /**
     * Minimal price for search.
     */
    private static final String MIN_PRICE_BORDER = "0.00";

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        String minBorder = request.getParameter("minPriceRange");
        String maxBorder = request.getParameter("maxPriceRange");
        if (minBorder.isEmpty() && maxBorder.isEmpty()) {
            return null;
        } else {
            if (!minBorder.isEmpty() && maxBorder.isEmpty()) {
                return findGreaterThanMinPrice(request, minBorder);
            } else if (minBorder.isEmpty() && !maxBorder.isEmpty()) {
                return findBetweenMinAndMaxBorder(request, MIN_PRICE_BORDER,
                        maxBorder);
            } else {
                return findBetweenMinAndMaxBorder(request, minBorder, maxBorder);
            }
        }
    }

    /**
     * Call method that search coupons with price greater than minimal price
     * range.
     *
     * @param request     request for search.
     * @param newMinPrice minimal price border for search coupons.
     * @return forward on some page.
     * @throws ServiceException if have exception.
     */
    private Forward findGreaterThanMinPrice(final HttpServletRequest request,
                                            final String newMinPrice)
            throws ServiceException {
        if (validator.validateCouponPrice(newMinPrice)) {
            BigDecimal minPrice = new BigDecimal(newMinPrice);
            CouponService couponService = (CouponService) factory
                    .createService(DAOEnum.COUPON);
            PagePagination pagination = new PagePagination(
                    couponService.getAmountGreaterThanCurrentPrice(minPrice),
                    ROWCOUNT, request.getParameter("page"));
            request.setAttribute("amountOfPages", pagination
                    .getPagesAmount());
            List<Coupon> resultCoupons = couponService
                    .getAllGreaterThanCurrentPrice(minPrice,
                            pagination.getPageOffset(), ROWCOUNT);
            Forward forward = new Forward("/coupons.html");
            forward.getAttributes().put("resultCoupons", resultCoupons);
            forward.getAttributes().put("amountOfPages", pagination
                    .getPagesAmount());
            forward.getAttributes().put("paginationURL", "/coupons.html");
            return forward;
        } else {
            request.setAttribute("message", "incorrectPriceValue");
            return null;
        }
    }

    /**
     * Call method that search coupons with price lower than maximal price
     * range.
     *
     * @param request     request for search.
     * @param newMaxPrice maximal price border for search coupons.
     * @return forward to some page.
     * @throws ServiceException if have exception.
     */
    private Forward findLowerThanMaxPrice(final HttpServletRequest request,
                                          final String newMaxPrice)
            throws ServiceException {
        return findBetweenMinAndMaxBorder(request, "0.0",
                newMaxPrice);
    }

    /**
     * Call method that search coupons with price between maximal and maximal
     * price range.
     *
     * @param request     request for search.
     * @param newMinPrice minimal price border for search coupons.
     * @param newMaxPrice maximal price border for search coupons.
     */
    private Forward findBetweenMinAndMaxBorder(final HttpServletRequest request,
                                               final String newMinPrice,
                                               final String newMaxPrice)
            throws ServiceException {
        if (validator.validatePriceRange(newMinPrice, newMaxPrice)) {
            BigDecimal minPrice = new BigDecimal(newMinPrice);
            BigDecimal maxPrice = new BigDecimal(newMaxPrice);
            CouponService couponService = (CouponService) factory
                    .createService(DAOEnum.COUPON);
            PagePagination pagination = new PagePagination(
                    couponService.getAmountByPriceRange(minPrice, maxPrice),
                    ROWCOUNT, request.getParameter("page"));
            request.setAttribute("amountOfPages", pagination
                    .getPagesAmount());
            List<Coupon> resultCoupons = couponService
                    .getAllByPriceRange(minPrice, maxPrice, pagination
                            .getPageOffset(), ROWCOUNT);
            Forward forward = new Forward("/coupons.html");
            forward.getAttributes().put("resultCoupons", resultCoupons);
            forward.getAttributes().put("amountOfPages", pagination
                    .getPagesAmount());
            forward.getAttributes().put("paginationURL", "/coupons.html");
            return forward;
        } else {
            Forward forward = new Forward("/coupons.html");
            forward.getAttributes().put("message", "incorrectPriceRange");
            return null;
        }
    }
}
