package entity;

import emun.CardType;
import jakarta.persistence.*;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "card")
public class Card extends AuditableEntity{

    public Card() {

    }

    public Card( String cardNumber, LocalDateTime expirationDate, String cvv, CardType cardType, Boolean isExpired, Boolean isActive, Integer version, Integer depositId) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.cardType = cardType;
        this.isExpired = isExpired;
        this.isActive = isActive;
        this.version = version;
        this.depositId = depositId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(unique = true, nullable = false)
    private String cardNumber;


    private LocalDateTime expirationDate;

    private String cvv;

    @Enumerated(EnumType.STRING)

    private CardType cardType;

    private Boolean isExpired;

    private Boolean isActive;

    @Version
    private Integer version;


    private Integer depositId;



    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv='" + cvv + '\'' +
                ", cardType=" + cardType +
                ", isExpired=" + isExpired +
                ", isActive=" + isActive +
                ", depositId=" + depositId+
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardId, card.cardId) &&
                Objects.equals(cardNumber, card.cardNumber) &&
                Objects.equals(expirationDate, card.expirationDate) &&
                cardType == card.cardType &&
                Objects.equals(cvv, card.cvv) &&
                Objects.equals(isExpired, card.isExpired) &&
                Objects.equals(isActive, card.isActive) &&
                Objects.equals(depositId,card.depositId)&&
                Objects.equals(version, card.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, cardNumber, expirationDate, cvv, cardType, isExpired, isActive, version,depositId);
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDepositId() {
        return depositId;
    }

    public void setDepositId(Integer depositId) {
        this.depositId = depositId;
    }
}