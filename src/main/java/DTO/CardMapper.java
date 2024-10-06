package DTO;

import entity.Card;

public class CardMapper {
    // Convert Card to CardDTO
    public static CardDTO toDTO(Card card) {
        if (card == null) {
            return null;
        }

        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardId(card.getCardId());
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setExpirationDate(card.getExpirationDate());
        cardDTO.setCvv(card.getCvv());
        cardDTO.setCardType(card.getCardType());
        cardDTO.setExpired(card.getExpired());
        cardDTO.setActive(card.getActive());
        cardDTO.setVersion(card.getVersion());
        cardDTO.setDepositId(card.getDepositId());

        return cardDTO;
    }

    // Convert CardDTO to Card
    public static Card toEntity(CardDTO cardDTO) {
        if (cardDTO == null) {
            return null;
        }

        Card card = new Card();
        card.setCardId(cardDTO.getCardId());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setExpirationDate(cardDTO.getExpirationDate());
        card.setCvv(cardDTO.getCvv());
        card.setCardType(cardDTO.getCardType());
        card.setExpired(cardDTO.getExpired());
        card.setActive(cardDTO.getActive());
        card.setVersion(cardDTO.getVersion());
        card.setDepositId(cardDTO.getDepositId());

        return card;
    }
}