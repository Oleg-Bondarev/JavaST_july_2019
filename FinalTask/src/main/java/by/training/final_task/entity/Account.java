package by.training.final_task.entity;

import java.util.Objects;

/**
 * Represent admin and user account.
 * */
public class Account {
    /**
     * Login.
     * */
    private String accountLogin;
    /**
     * Password.
     * */
    private String accountPassword;
    /**
     * E-mail.
     * */
    private String accountEmail;
    /**
     * First name.
     * */
    private String firstName;
    /**
     * Second name.
     * */
    private String secondName;
    /**
     * Mobile phone.
     * */
    private String mobilePhone;
    /**
     * Default constructor.
     * */
    public Account() { }
    /**
     * Getter.
     * @return login.
     * */
    public String getAccountLogin() {
        return accountLogin;
    }
    /**
     * Setter.
     * @param newAccountLogin -new login.
     * */
    public void setAccountLogin(final String newAccountLogin) {
        accountLogin = newAccountLogin;
    }
    /**
     * Getter.
     * @return password.
     * */
    public String getAccountPassword() {
        return accountPassword;
    }
    /**
     * Setter.
     * @param newAccountPassword -new password.
     * */
    public void setAccountPassword(final String newAccountPassword) {
        accountPassword = newAccountPassword;
    }
    /**
     * Getter.
     * @return e-mail.
     * */
    public String getAccountEmail() {
        return accountEmail;
    }
    /**
     * Setter.
     * @param newAccountEmail -new e-mail.
     * */
    public void setAccountEmail(final String newAccountEmail) {
        accountEmail = newAccountEmail;
    }
    /**
     * Getter.
     * @return first name.
     * */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Setter.
     * @param newFirstName -new first name.
     * */
    public void setFirstName(final String newFirstName) {
        firstName = newFirstName;
    }
    /**
     * Getter.
     * @return second name.
     * */
    public String getSecondName() {
        return secondName;
    }
    /**
     * Setter.
     * @param newSecondName -new second name.
     * */
    public void setSecondName(final String newSecondName) {
        secondName = newSecondName;
    }
    /**
     * Getter.
     * @return mobile number.
     * */
    public String getMobilePhone() {
        return mobilePhone;
    }
    /**
     * Setter.
     * @param newMobilePhone -new mobile number.
     * */
    public void setMobilePhone(final String newMobilePhone) {
        mobilePhone = newMobilePhone;
    }
    /**
     * Equals method.
     * @param newO -object to compare.
     * @return boolean value.
     * */
    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        Account account = (Account) newO;
        return accountLogin.equals(account.accountLogin)
                && accountPassword.equals(account.accountPassword)
                && accountEmail.equals(account.accountEmail)
                && firstName.equals(account.firstName)
                && secondName.equals(account.secondName)
                && mobilePhone.equals(account.mobilePhone);
    }
    /**
     * HashCode method.
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(accountLogin, accountPassword, accountEmail,
                firstName, secondName, mobilePhone);
    }
    /**
     * To string method.
     * @return string class representation.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Account{");
        builder.append("accountLogin=");
        builder.append(accountLogin);
        builder.append(", accountPassword=");
        builder.append(accountPassword);
        builder.append(", accountEmail=");
        builder.append(accountEmail);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", secondName=");
        builder.append(secondName);
        builder.append(", mobilePhone=");
        builder.append(mobilePhone);
        builder.append("}");
        return builder.toString();
    }
}
