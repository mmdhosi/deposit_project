package DTO;


import emun.AccountType;
import java.time.LocalDateTime;

public class DepositDTO {

    private Integer depositId;
    private Integer customerId;
    private Integer addressId;
    private String customerNumber;
    private String depositNumber;
    private Integer balance;
    private AccountType accountType;
    private Boolean isExpired;
    private Boolean isActive;
    private LocalDateTime endDate;
    private Integer version;



    // Default constructor
    public DepositDTO() {}

    // All-args constructor
    public DepositDTO(Integer depositId, String depositNumber, Integer balance, AccountType accountType,
                      Boolean isExpired, Boolean isActive, LocalDateTime endDate, Integer version) {
        this.depositId = depositId;
        this.depositNumber = depositNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.isExpired = isExpired;
        this.isActive = isActive;
        this.endDate = endDate;
        this.version = version;
    }

    // Getters and Setters
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
    public Integer getDepositId() {
        return depositId;
    }

    public void setDepositId(Integer depositId) {
        this.depositId = depositId;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
