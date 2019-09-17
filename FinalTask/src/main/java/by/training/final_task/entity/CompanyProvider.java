package by.training.final_task.entity;

import java.util.Objects;

/**
 * Represent the provider of the coupon.
 * */
public class CompanyProvider extends Entity {
    private String companyAddress;
    private String companyName;
    private int mobilePhone;

    public CompanyProvider(final String newCompanyAddress,
                           final String newCompanyName,
                           final int newMobilePhone) {
        companyAddress = newCompanyAddress;
        companyName = newCompanyName;
        mobilePhone = newMobilePhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(final String newCompanyAddress) {
        companyAddress = newCompanyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(final String newCompanyName) {
        companyName = newCompanyName;
    }

    public int getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(final int newMobilePhone) {
        mobilePhone = newMobilePhone;
    }
/////////id????
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
        CompanyProvider that = (CompanyProvider) newO;
        return mobilePhone == that.mobilePhone
                && companyAddress.equals(that.companyAddress)
                && companyName.equals(that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyAddress, companyName,
                mobilePhone);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Company provider{");
        builder.append(super.toString());
        builder.append(", company mame=");
        builder.append(companyName);
        builder.append(", company address=");
        builder.append(companyAddress);
        builder.append("}");
        return builder.toString();
    }
}
