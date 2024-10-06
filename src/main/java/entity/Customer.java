package entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer extends AuditableEntity {

    public Customer()  {

    }

    public Customer( String customerNumber, String firstName, String lastName, String email, String phone, LocalDateTime dateOfBirth, Integer version, Boolean isActive) {
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.version = version;
        this.isActive = isActive;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;


    @NotEmpty(message = "Customer number cannot be empty")
    @Column(unique = true, nullable = false)
    private String customerNumber;


    private String firstName;


    private String lastName;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,}$", message = "Phone number should be valid")
    private String phone;

    @Past(message = "Date of Birth must be in the past")
    private LocalDateTime dateOfBirth;

    @Min(value = 1, message = "Version must be greater than 0")
    @Version
    private Integer version;

    private Boolean isActive;




    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerNumber='" + customerNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", version=" + version +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(customerNumber, customer.customerNumber) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(dateOfBirth, customer.dateOfBirth) &&
                Objects.equals(version, customer.version) &&
                Objects.equals(isActive, customer.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerNumber, firstName, lastName, email, phone, dateOfBirth, version, isActive);
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}