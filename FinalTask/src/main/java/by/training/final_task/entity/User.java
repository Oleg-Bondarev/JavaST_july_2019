package by.training.final_task.entity;

import java.util.Objects;

/**
 * Represent admin and user account.
 * */
public class User extends Entity{
    private String pathToAvatar;
    /**
     * User role.
     * */
    private byte role;
    /**
     * Login.
     * */
    private String login;
    /**
     * Password.
     * */
    private String password;
    /**
     * E-mail.
     * */
    private String email;
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
    public User() { }

    public String getPathToAvatar() {
        return pathToAvatar;
    }

    public void setPathToAvatar(final String newPathToAvatar) {
        pathToAvatar = newPathToAvatar;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(final byte newRole) {
        role = newRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String newLogin) {
        login = newLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String newPassword) {
        password = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String newEmail) {
        email = newEmail;
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
        User user = (User) newO;
        return role == user.role && login.equals(user.login)
                && password.equals(user.password)
                && pathToAvatar.equals(user.pathToAvatar)
                && email.equals(user.email) && firstName.equals(user.firstName)
                && secondName.equals(user.secondName)
                && mobilePhone.equals(user.mobilePhone);
    }
    /**
     * HashCode method.
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pathToAvatar, role, login,
                password, email, firstName, secondName, mobilePhone);
    }
    /**
     * To string method.
     * @return string class representation.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Account{");
        builder.append(super.toString());
        builder.append(", role=");
        builder.append(role);
        builder.append(", login=");
        builder.append(login);
        builder.append(", password=");
        builder.append(password);
        builder.append(", e-mail=");
        builder.append(email);
        builder.append(", first name=");
        builder.append(firstName);
        builder.append(", second name=");
        builder.append(secondName);
        builder.append(", mobile phone=");
        builder.append(mobilePhone);
        builder.append("}");
        return builder.toString();
    }
}
