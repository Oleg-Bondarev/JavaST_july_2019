package by.training.final_task.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents coupon.
 * */
public class Coupon {
    /**
     * Coupon name.
     * */
    private String couponName;
    /**
     * Coupon category.
     * */
    private String couponCategory;
    /**
     * Coupon description.
     * */
    private String couponDescription;
    /**
     * Coupon price.
     * */
    private double couponPrice;
    /**
     * Coupon rating.
     * Count stars. Min - 0; Max - 5;
     * */
    private int countStars;
    /**
     * Coupon add date.
     * */
    private LocalDateTime couponAddDate;
    /**
     * Holding address.
     * */
    private String holdingAddress;
    /**
     * Default constructor.
     * */
    public Coupon() {
        this.countStars = 0;
    }
    /**
     * Getter.
     * @return coupon name.
     * */
    public String getCouponName() {
        return couponName;
    }
    /**
     * Setter.
     * @param newCouponName -new coupon name.
     * */
    public void setCouponName(final String newCouponName) {
        couponName = newCouponName;
    }
    /**
     * Getter.
     * @return coupon category.
     * */
    public String getCouponCategory() {
        return couponCategory;
    }
    /**
     * Setter.
     * @param newCouponCategory -new category.
     * */
    public void setCouponCategory(final String newCouponCategory) {
        couponCategory = newCouponCategory;
    }
    /**
     * Getter.
     * @return coupon description.
     * */
    public String getCouponDescription() {
        return couponDescription;
    }
    /**
     * Setter.
     * @param newCouponDescription -coupon description.
     * */
    public void setCouponDescription(final String newCouponDescription) {
        couponDescription = newCouponDescription;
    }
    /**
     * Getter.
     * @return coupon price.
     * */
    public double getCouponPrice() {
        return couponPrice;
    }
    /**
     * Setter.
     * @param newCouponPrice -new price.
     * */
    public void setCouponPrice(final double newCouponPrice) {
        couponPrice = newCouponPrice;
    }
    /**
     * Getter.
     * @return coupon rating.
     * */
    public int getCountStars() {
        return countStars;
    }
    /**
     * Setter.
     * @param newCountStars -new coupon rating.
     * */
    public void setCountStars(final int newCountStars) {
        countStars = newCountStars;
    }
    /**
     * Getter.
     * @return registration date.
     * */
    public LocalDateTime getCouponAddDate() {
        return couponAddDate;
    }
    /**
     * Setter.
     * @param newCouponAddDate -registration date.
     * */
    public void setCouponAddDate(final LocalDateTime newCouponAddDate) {
        couponAddDate = newCouponAddDate;
    }
    /**
     * Getter.
     * @return holding address.
     * */
    public String getHoldingAddress() {
        return holdingAddress;
    }
    /**
     * Setter.
     * @param newHoldingAddress -new holding address.
     * */
    public void setHoldingAddress(final String newHoldingAddress) {
        holdingAddress = newHoldingAddress;
    }
    /**
     * @param newO -object to compare.
     * */
    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        Coupon coupon = (Coupon) newO;
        return Double.compare(coupon.couponPrice, couponPrice) == 0
                && countStars == coupon.countStars
                && couponName.equals(coupon.couponName)
                && couponCategory.equals(coupon.couponCategory)
                && couponDescription.equals(coupon.couponDescription)
                && couponAddDate.equals(coupon.couponAddDate)
                && holdingAddress.equals(coupon.holdingAddress);
    }
    /**
     * HashCode method.
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(couponName, couponCategory, couponDescription,
                couponPrice, countStars, couponAddDate, holdingAddress);
    }
    /**
     * To string method.
     * @return string class representation.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Coupon{");
        builder.append("coupon mame=");
        builder.append(couponName);
        builder.append(", coupon category=");
        builder.append(couponCategory);
        builder.append(", coupon description=");
        builder.append(couponDescription);
        builder.append(", coupon price=");
        builder.append(couponPrice);
        builder.append(", count stars=");
        builder.append(countStars);
        builder.append(", coupon add date=");
        builder.append(couponAddDate);
        builder.append(", holding address=");
        builder.append(holdingAddress);
        builder.append("}");
        return builder.toString();
    }
}
