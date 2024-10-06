package service;

import DTO.CustomerDTO;
import DTO.DepositDTO;
import Repository.CardRepository;
import Repository.CardRepositoryImpl;
import entity.Card;
import DTO.CardDTO;
import DTO.CardMapper;
import entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@ApplicationScoped
@Transactional
public class CardService {

    @Inject
    private CardRepositoryImpl cardRepository; // Inject the repository

    @Inject
    private CardMapper cardMapper; // Inject the mapper
    @Inject
    private DepositService depositService;


    public CardDTO createCard(CardDTO cardDTO) {
        cardDTO.setCardNumber(generateUniqueCardNumber());
        cardDTO.setActive(true);
        cardDTO.setExpired(false);
        validateCard(cardDTO); // Validate the DTO
        Card card = cardMapper.toEntity(cardDTO); // Convert DTO to Entity
        Card savedCard = cardRepository.save(card); // Save the entity
        return cardMapper.toDTO(savedCard); // Convert saved entity back to DTO
    }

    public Optional<CardDTO> findCardById(Integer id) {
        Card card = cardRepository.findById(id);
        return Optional.ofNullable(cardMapper.toDTO(card)); // Map to DTO and handle null
    }

    public List<CardDTO> findAllCards() {
        List<Card> cards = cardRepository.findAll();
        List<CardDTO>cardDTOS = new ArrayList<CardDTO>();
        for (Card c:cards) {
            cardDTOS.add(cardMapper.toDTO(c));
        }
        return cardDTOS;
    }
    public List<CardDTO> findByDepositId(Integer id){
        return cardRepository.findAllByDepositId(id);
    }

    public DepositDTO findDeposit(Integer id){
        return depositService.findDepositById(cardRepository.findById(id).getDepositId());
    }

    public List<CustomerDTO> findCustomers(Integer id){
        return depositService.findCustomers(cardRepository.findById(id).getDepositId());
    }

    public boolean deleteCard(Integer id) {
        return cardRepository.deleteById(id);
    }

    public void updateCard(CardDTO cardDTO) {
        validateCard(cardDTO); // Validate the DTO
        Card card = cardMapper.toEntity(cardDTO); // Convert DTO to Entity
        cardRepository.save(card); // Save the updated entity
    }

    public long countCards() {
        return cardRepository.count();
    }

    public boolean existsCardById(Integer id) {
        return cardRepository.existsById(id);
    }

    public List<CardDTO> findByCardNumber(String cardNumber) {
        List<Card> cards = cardRepository.findByCardNumber(cardNumber);
        List<CardDTO>cardDTOS = new ArrayList<CardDTO>();
        for (Card c:cards) {
            cardDTOS.add(cardMapper.toDTO(c));
        }
        return cardDTOS;
    }

    private void validateCard(CardDTO cardDTO) {
        if (cardDTO.getCardNumber() == null || !isValidCardNumber(cardDTO.getCardNumber())) {
            throw new IllegalArgumentException("Card number is invalid");
        }
        if (cardDTO.getExpirationDate() == null || cardDTO.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Expiration date must be in the future");
        }
        if (cardDTO.getCvv() == null || cardDTO.getCvv().length() < 3) {
            throw new IllegalArgumentException("CVV must be at least 3 digits");
        }
        if (cardDTO.getCardType() == null) {
            throw new IllegalArgumentException("Card type cannot be null");
        }
//        if (cardDTO.getVersion() == null || cardDTO.getVersion() < 1) {
//            throw new IllegalArgumentException("Card version must be greater than 0");
//        }
        // Check uniqueness of card number
        if (!checkCardNumberUniqueness(cardDTO.getCardNumber())) {
            throw new IllegalArgumentException("Card number already exists");
        }
        if(checkDepositId(cardDTO.getDepositId())){
            throw new IllegalArgumentException("deposit does not exist");
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.length() == 16 && Pattern.matches("^[0-9]+$", cardNumber);
    }

    private boolean checkCardNumberUniqueness(String cardNumber) {
        List<CardDTO> existingCards = findByCardNumber(cardNumber);
        return existingCards.isEmpty();
    }
    private String generateUniqueCardNumber() {
        String cardNumber;
        do {
            cardNumber = generateRandomCardNumber(); // Generate 16-digit card number
        } while (!checkCardNumberUniqueness(cardNumber)); // Check for uniqueness
        return cardNumber;
    }

    private String generateRandomCardNumber() {
        StringBuilder cardNumberBuilder = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            cardNumberBuilder.append((int) (Math.random() * 10)); // Generates a digit between 0-9
        }
        return cardNumberBuilder.toString();
    }
    private Boolean checkDepositId(Integer depositId){
        return depositService.findDepositById(depositId) == null;
    }


}