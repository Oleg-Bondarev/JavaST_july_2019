package by.training.final_task.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents coupon.
 * */
public class Coupon extends Entity {
    private String pathToImg;
    /**
     * Coupon name.
     * */
    private String couponName;
    /**
     * Coupon description.
     * */
    private String couponDescription;
    /**
     * Coupon price.
     * */
    private BigDecimal couponPrice;
    /**
     * Coupon add date.
     * */
    private LocalDateTime couponAddDate;
    /**
     * Holding address.
     * */
    private String holdingAddress;

    public String getPathToImg() {
        return pathToImg;
    }

    public void setPathToImg(final String newPathToImg) {
        pathToImg = newPathToImg;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(final BigDecimal newCouponPrice) {
        couponPrice = newCouponPrice;
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
        return couponName.equals(coupon.couponName)
                && couponDescription.equals(coupon.couponDescription)
                && couponPrice.equals(coupon.couponPrice)
                && couponAddDate.equals(coupon.couponAddDate)
                && pathToImg.equals(coupon.pathToImg)
                && holdingAddress.equals(coupon.holdingAddress);
    }
    /**
     * HashCode method.
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pathToImg, couponName,
                couponDescription, couponPrice, couponAddDate, holdingAddress);
    }
    /**
     * To string method.
     * @return string class representation.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Coupon{");
        builder.append(super.toString());
        builder.append("coupon mame=");
        builder.append(couponName);
        builder.append(", coupon description=");
        builder.append(couponDescription);
        builder.append(", coupon price=");
        builder.append(couponPrice);
        builder.append(", coupon add date=");
        builder.append(couponAddDate);
        builder.append(", holding address=");
        builder.append(holdingAddress);
        builder.append("}");
        return builder.toString();
    }
}
