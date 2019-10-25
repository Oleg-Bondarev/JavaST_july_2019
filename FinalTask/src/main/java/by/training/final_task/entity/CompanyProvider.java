package by.training.final_task.entity;

import java.util.Objects;

/**
 * Represent the provider of the coupon.
 * */
public class CompanyProvider extends Entity {
    /**
     * Company address.
     * */
    private String companyAddress;
    /**
     * Company provider name.
     * */
    private String companyName;
    /**
     * Company contact mobile phone.
     * */
    private int mobilePhone;
    /**
     * Company blocking status.
     * */
    private boolean blocking;

    /**
     * Constructor.
     * @param newId ID to be set to {@link #id}
     * @param newCompanyAddress company address to be set
     *                         to {@link #companyAddress}
     * @param newCompanyName name to be set to {@link #companyName}
     * @param newMobilePhone phone to be set to {@link #mobilePhone}
     * @param newBlocking status to be set to {@link #blocking}
     * */
    public CompanyProvider(final long newId, final String newCompanyAddress,
                           final String newCompanyName,
                       final int newMobilePhone, final boolean newBlocking) {
        id = newId;
        companyAddress = newCompanyAddress;
        companyName = newCompanyName;
        mobilePhone = newMobilePhone;
        blocking = newBlocking;
    }
    /**
     * Default constructor.
     * */
    public CompanyProvider() { }
    /**
     * @param newId ID to be set to {@link #id}
     * */
    public CompanyProvider(final long newId) {
        id = newId;
    }
    /**
     * Return company address.
     * @return company address.
     * */
    public String getCompanyAddress() {
        return companyAddress;
    }
    /**
     * Set company address.
     * @param newCompanyAddress address to be set to {@link #companyAddress}
     * */
    public void setCompanyAddress(final String newCompanyAddress) {
        companyAddress = newCompanyAddress;
    }
    /**
     * Return company name.
     * @return company name.
     * */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * Set company name.
     * @param newCompanyName name to be set to {@link #companyName}
     * */
    public void setCompanyName(final String newCompanyName) {
        companyName = newCompanyName;
    }
    /**
     * Return company phone.
     * @return company phone.
     * */
    public int getMobilePhone() {
        return mobilePhone;
    }
    /**
     * Set new mobile phone.
     * @param newMobilePhone phone to be set to {@link #mobilePhone}
     * */
    public void setMobilePhone(final int newMobilePhone) {
        mobilePhone = newMobilePhone;
    }
    /**
     * @return company blocking status.
     * */
    public boolean getBlocking() {
        return blocking;
    }
    /**
     * Set new blocking status for company.
     * @param newBlocking status to be set to {@link #blocking}
     * */
    public void setBlocking(final boolean newBlocking) {
        blocking = newBlocking;
    }

    /**
     * Hashcode method.
     * @return object hashcode.
     * */
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
                && companyName.equals(that.companyName)
                && blocking == that.blocking;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyAddress, companyName,
                mobilePhone, blocking);
    }
    /**
     * @return object string representation.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Company provider{");
        builder.append(super.toString());
        builder.append(", company mame=");
        builder.append(companyName);
        builder.append(", company address=");
        builder.append(companyAddress);
        builder.append(", blocking=");
        builder.append(blocking);
        builder.append("}");
        return builder.toString();
    }
}
