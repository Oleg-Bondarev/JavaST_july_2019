package by.training.final_task.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * Represents coupon.
 * */
public class Coupon extends Entity {
    private String couponName;
    private String pathToPicture;
    private String couponDescription;
    private BigDecimal couponPrice;
    private Date couponAddDate;
    private String holdingAddress;
    private long categoryId;
    private long companyProviderId;

    public Coupon(final long newId, final String newCouponName, final String newPathToPicture,
                  final String newCouponDescription,
                  final BigDecimal newCouponPrice, final Date newCouponAddDate,
                  final String newHoldingAddress, final long newCategoryId,
                  final long newCompanyProviderId) {
        id = newId;
        couponName = newCouponName;
        pathToPicture = newPathToPicture;
        couponDescription = newCouponDescription;
        couponPrice = newCouponPrice;
        couponAddDate = newCouponAddDate;
        holdingAddress = newHoldingAddress;
        categoryId = newCategoryId;
        companyProviderId = newCompanyProviderId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(final String newCouponName) {
        couponName = newCouponName;
    }

    public String getPathToPicture() {
        return pathToPicture;
    }

    public void setPathToPicture(final String newPathToPicture) {
        pathToPicture = newPathToPicture;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(final String newCouponDescription) {
        couponDescription = newCouponDescription;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(final BigDecimal newCouponPrice) {
        couponPrice = newCouponPrice;
    }

    public Date getCouponAddDate() {
        return couponAddDate;
    }

    public void setCouponAddDate(final Date newCouponAddDate) {
        couponAddDate = newCouponAddDate;
    }

    public String getHoldingAddress() {
        return holdingAddress;
    }

    public void setHoldingAddress(final String newHoldingAddress) {
        holdingAddress = newHoldingAddress;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final long newCategoryId) {
        categoryId = newCategoryId;
    }

    public long getCompanyProviderId() {
        return companyProviderId;
    }

    public void setCompanyProviderId(final long newCompanyProviderId) {
        companyProviderId = newCompanyProviderId;
    }

    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        if (!super.equals(newO)) {
            return false;
        }
        Coupon coupon = (Coupon) newO;
        return couponName.equals(coupon.couponName)
                && pathToPicture.equals(coupon.pathToPicture)
                && couponDescription.equals(coupon.couponDescription)
                && couponPrice.equals(coupon.couponPrice)
                && couponAddDate.equals(coupon.couponAddDate)
                && holdingAddress.equals(coupon.holdingAddress)
                && categoryId == coupon.categoryId
                && companyProviderId == coupon.companyProviderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), couponName, pathToPicture,
                couponDescription, couponPrice, couponAddDate, holdingAddress,
                categoryId, companyProviderId);
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
        builder.append(", category id");
        builder.append(categoryId);
        builder.append(", company provider id=");
        builder.append(companyProviderId);
        builder.append("}");
        return builder.toString();
    }
}
