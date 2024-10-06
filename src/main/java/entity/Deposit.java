package entity;

import emun.AccountType;



import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "deposit")
public class Deposit extends AuditableEntity{

    public Deposit() {
    }

    public Deposit(String depositNumber, Integer balance, AccountType accountType, Boolean isExpired, Boolean isActive, LocalDateTime endDate,  Integer version) {
        this.depositNumber = depositNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.isExpired = isExpired;
        this.isActive = isActive;
        this.endDate = endDate;
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer depositId;

    private String depositNumber;

    @Column(precision = 15, scale = 2)
    private Integer balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Boolean isExpired;

    private Boolean isActive;

    private LocalDateTime endDate;

    @Version
    private Integer version;




    @Override
    public String toString() {
        return "Deposit{" +
                "depositId=" + depositId +
                ", depositNumber='" + depositNumber + '\'' +
                ", balance=" + balance +
                ", accountType=" + accountType +
                ", isExpired=" + isExpired +
                ", isActive=" + isActive +
                ", endDate=" + endDate +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return Objects.equals(depositId, deposit.depositId) &&
                Objects.equals(depositNumber, deposit.depositNumber) &&
                Objects.equals(balance, deposit.balance) &&
                accountType == deposit.accountType &&
                Objects.equals(isExpired, deposit.isExpired) &&
                Objects.equals(isActive, deposit.isActive) &&
                Objects.equals(endDate, deposit.endDate) &&
                Objects.equals(version, deposit.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depositId, depositNumber, balance, accountType, isExpired, isActive, endDate,  version);
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