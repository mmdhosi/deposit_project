package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customerDeposit")
public class CustomerDeposit extends AuditableEntity{

    public CustomerDeposit() {
    }

    public CustomerDeposit(Integer customerId, Integer depositId, Integer addressId, Boolean isExpired, Boolean isActive) {
        this.customerId = customerId;
        this.depositId = depositId;
        this.addressId = addressId;
        this.isExpired = isExpired;
        this.isActive = isActive;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerDepositId;


    private Integer customerId;


    private Integer depositId;


    private Integer addressId;


    private Boolean isExpired;

    private Boolean isActive;

    public Integer getCustomerDepositId() {
        return customerDepositId;
    }

    public void setCustomerDepositId(Integer customerDepositId) {
        this.customerDepositId = customerDepositId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDepositId() {
        return depositId;
    }

    public void setDepositId(Integer depositId) {
        this.depositId = depositId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDeposit that = (CustomerDeposit) o;
        return customerDepositId.equals(that.customerDepositId) && customerId.equals(that.customerId) && depositId.equals(that.depositId) && addressId.equals(that.addressId) && Objects.equals(isExpired, that.isExpired) && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerDepositId, customerId, depositId, addressId, isExpired, isActive);
    }

    @Override
    public String toString() {
        return "CustomerDeposit{" +
                "customerDepositId=" + customerDepositId +
                ", customerId=" + customerId +
                ", depositId=" + depositId +
                ", addressId=" + addressId +
                ", isExpired=" + isExpired +
                ", isActive=" + isActive +
                '}';
    }

}