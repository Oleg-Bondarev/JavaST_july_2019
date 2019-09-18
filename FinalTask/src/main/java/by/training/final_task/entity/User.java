package by.training.final_task.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * Represent admin and user account.
 * */
public class User extends Entity {
    private String login;
    private String password;
    private Role role;
    private String email;
    private String pathToAvatar;
    private String firstName;
    private String secondName;
    private int mobilePhone;
    private Date registrationDate;

    public User(final long newId, final String newLogin, final String newPassword,
                final Role newRole, final String newEmail,
                final String newPathToAvatar, final String newFirstName,
                final String newSecondName, final int newMobilePhone,
                final Date newRegistrationDate) {
        id = newId;
        login = newLogin;
        password = newPassword;
        role = newRole;
        email = newEmail;
        pathToAvatar = newPathToAvatar;
        firstName = newFirstName;
        secondName = newSecondName;
        mobilePhone = newMobilePhone;
        registrationDate = newRegistrationDate;
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

    public Role getRole() {
        return role;
    }

    public void setRole(final Role newRole) {
        role = newRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String newEmail) {
        email = newEmail;
    }

    public String getPathToAvatar() {
        return pathToAvatar;
    }

    public void setPathToAvatar(final String newPathToAvatar) {
        pathToAvatar = newPathToAvatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String newFirstName) {
        firstName = newFirstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(final String newSecondName) {
        secondName = newSecondName;
    }

    public int getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(final int newMobilePhone) {
        mobilePhone = newMobilePhone;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(final Date newRegistrationDate) {
        registrationDate = newRegistrationDate;
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
        User user = (User) newO;
        return role == user.role && mobilePhone == user.mobilePhone
                && login.equals(user.login) && password.equals(user.password)
                && email.equals(user.email)
                && pathToAvatar.equals(user.pathToAvatar)
                && firstName.equals(user.firstName)
                && secondName.equals(user.secondName)
                && registrationDate.equals(user.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, role, email,
                pathToAvatar, firstName, secondName, mobilePhone,
                registrationDate);
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
        builder.append(", registration date=");
        builder.append(registrationDate);
        builder.append("}");
        return builder.toString();
    }
}
