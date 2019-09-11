package by.training.final_task.entity;

import java.util.Objects;

/**
 * Represent the provider of the coupon.
 * */
public class CompanyProvider {
    /**
     * Company name.
     * */
    private String companyName;
    /**
     * Company address.
     * */
    private String companyAddress;
    /**
     * Default constructor.
     * */
    public CompanyProvider() { }
    /**
     * Getter.
     * @return company name.
     * */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * Setter.
     * @param newCompanyName -new name.
     * */
    public void setCompanyName(final String newCompanyName) {
        companyName = newCompanyName;
    }
    /**
     * Getter.
     * @return address.
     * */
    public String getCompanyAddress() {
        return companyAddress;
    }
    /**
     * Setter.
     * @param newCompanyAddress -new address.
     * */
    public void setCompanyAddress(final String newCompanyAddress) {
        companyAddress = newCompanyAddress;
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
        CompanyProvider that = (CompanyProvider) newO;
        return companyName.equals(that.companyName)
                && companyAddress.equals(that.companyAddress);
    }
    /**
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(companyName, companyAddress);
    }
    /**
     * @return string class representation.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Company provider{");
        builder.append("company mame=");
        builder.append(companyName);
        builder.append(", company address=");
        builder.append(companyAddress);
        builder.append("}");
        return builder.toString();
    }
}
