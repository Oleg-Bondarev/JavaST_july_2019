package by.training.final_task.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents customer.
 * */
public class Customer extends Account {
    /**
     * Customer birthday.
     * */
    private LocalDate customerBirthday;
    /**
     * Customer registration date.
     * */
    private LocalDateTime registrationDateTime;
    /**
     * Default constructor.
     * */
    public Customer() {
        super();
    }
    /**
     * @return customer birthday.
     * */
    public LocalDate getCustomerBirthday() {
        return customerBirthday;
    }
    /**
     * @param newCustomerBirthday -mew birthday.
     * */
    public void setCustomerBirthday(final LocalDate newCustomerBirthday) {
        customerBirthday = newCustomerBirthday;
    }
    /**
     * @return customer registration date and time in system.
     * */
    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }
    /**
     * @param newRegistrationDateTime -new date and time of registration.
     * */
    public void setRegistrationDateTime(final LocalDateTime
                                                newRegistrationDateTime) {
        registrationDateTime = newRegistrationDateTime;
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
        if (!super.equals(newO)) {
            return false;
        }
        Customer customer = (Customer) newO;
        return customerBirthday.equals(customer.customerBirthday)
                && registrationDateTime.equals(customer.registrationDateTime);
    }
    /**
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerBirthday,
                registrationDateTime);
    }
    /**
     * @return strong representation.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer{");
        builder.append("customer birthday=");
        builder.append(customerBirthday);
        builder.append(", registration date and time=");
        builder.append(registrationDateTime);
        builder.append("}");
        return builder.toString();
    }
}
