package by.training.final_task.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents coupon.
 * */
public class Coupon extends Entity {
    private String couponName;
    private String pathToPicture;
    private String couponDescription;
    private BigDecimal couponPrice;
    private LocalDate couponAddDate;
    private String holdingAddress;
    private Category category;
    private CompanyProvider companyProvider;
    private boolean blocking;

    public Coupon() { }

    public Coupon(final long newId, final String newCouponName, final String newPathToPicture,
                  final String newCouponDescription,
                  final BigDecimal newCouponPrice, final LocalDate newCouponAddDate,
                  final String newHoldingAddress, final Category newCategory,
                  final CompanyProvider newCompanyProvider, final boolean newBlocking) {
        id = newId;
        couponName = newCouponName;
        pathToPicture = newPathToPicture;
        couponDescription = newCouponDescription;
        couponPrice = newCouponPrice;
        couponAddDate = newCouponAddDate;
        holdingAddress = newHoldingAddress;
        category = newCategory;
        companyProvider = newCompanyProvider;
        blocking = newBlocking;
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

    public LocalDate  getCouponAddDate() {
        return couponAddDate;
    }

    public void setCouponAddDate(final LocalDate newCouponAddDate) {
        couponAddDate = newCouponAddDate;
    }

    public String getHoldingAddress() {
        return holdingAddress;
    }

    public void setHoldingAddress(final String newHoldingAddress) {
        holdingAddress = newHoldingAddress;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category newCategory) {
        category = newCategory;
    }

    public CompanyProvider getCompanyProvider() {
        return companyProvider;
    }

    public void setCompanyProvider(final CompanyProvider newCompanyProvider) {
        companyProvider = newCompanyProvider;
    }

    public boolean isBlocking() {
        return blocking;
    }

    public void setBlocking(final boolean newBlocking) {
        blocking = newBlocking;
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
                && category.equals(coupon.category)
                && companyProvider.equals(coupon.companyProvider)
                && blocking == coupon.blocking;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), couponName, pathToPicture,
                couponDescription, couponPrice, couponAddDate, holdingAddress,
                category, companyProvider, blocking);
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
        builder.append(", category =");
        builder.append(category);
        builder.append(", company provider=");
        builder.append(companyProvider);
        builder.append(", blocking=");
        builder.append(blocking);
        builder.append("}");
        return builder.toString();
    }
}
