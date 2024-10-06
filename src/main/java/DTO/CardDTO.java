package DTO;

import emun.CardType;

import java.time.LocalDateTime;
import java.util.Objects;

public class CardDTO {
    private Integer cardId;
    private String cardNumber;
    private LocalDateTime expirationDate;
    private String cvv;
    private CardType cardType;
    private Boolean isExpired;
    private Boolean isActive;
    private Integer version;
    private Integer depositId;

    // Constructors
    public CardDTO() {
    }

    public CardDTO(Integer cardId, String cardNumber, LocalDateTime expirationDate, String cvv, CardType cardType, Boolean isExpired, Boolean isActive, Integer version, Integer depositId) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.cardType = cardType;
        this.isExpired = isExpired;
        this.isActive = isActive;
        this.version = version;
        this.depositId = depositId;
    }

    // Getters and Setters
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