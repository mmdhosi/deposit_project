package entity;

import emun.TransactionType;



import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transaction")
public class Transaction extends AuditableEntity{

    public Transaction() {
    }

    public Transaction(Integer fromDepositId, Integer toDepositId, Integer amount, TransactionType transactionType, Integer transactionFee, Boolean isReverted, String description, Integer version) {
        this.fromDepositId = fromDepositId;
        this.toDepositId = toDepositId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionFee = transactionFee;
        this.isReverted = isReverted;
        this.description = description;
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private Integer fromDepositId;

    private Integer toDepositId;

    @Column(precision = 15, scale = 2)
    private Integer amount;

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(precision = 15, scale = 2)
    private Integer transactionFee;

    private Boolean isReverted;

    private String description;
    @Version
    private Integer version;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", fromDepositId=" + fromDepositId +
                ", toDepositId=" + toDepositId +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", transactionFee=" + transactionFee +
                ", isReverted=" + isReverted +
                ", description='" + description + '\'' +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return Objects.equals(transactionId, transaction.transactionId) &&
                Objects.equals(fromDepositId, transaction.fromDepositId) &&
                Objects.equals(toDepositId, transaction.toDepositId) &&
                Objects.equals(amount, transaction.amount) &&
                transactionType == transaction.transactionType &&
                Objects.equals(transactionFee, transaction.transactionFee) &&
                Objects.equals(isReverted, transaction.isReverted) &&
                Objects.equals(description, transaction.description) &&
                Objects.equals(version, transaction.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, fromDepositId, toDepositId, amount, transactionType, transactionFee, isReverted, description, version);
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getFromDepositId() {
        return fromDepositId;
    }

    public void setFromDepositId(Integer fromDepositId) {
        this.fromDepositId = fromDepositId;
    }

    public Integer getToDepositId() {
        return toDepositId;
    }

    public void setToDepositId(Integer toDepositId) {
        this.toDepositId = toDepositId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(Integer transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Boolean getReverted() {
        return isReverted;
    }

    public void setReverted(Boolean reverted) {
        isReverted = reverted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}