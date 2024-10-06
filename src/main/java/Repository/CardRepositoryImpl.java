package Repository;


import DTO.CardDTO;
import entity.Card;
import entity.Customer;
import entity.CustomerDeposit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CardRepositoryImpl extends JpaRepositoryImpl<Card,Integer> implements CardRepository {


    public CardRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public CardRepositoryImpl() {
    }
    public List<Card> findByCardNumber(String cardNumber){
        Query query =  getEntityManager().createQuery("SELECT a FROM Card a WHERE a.cardNumber = :cardNumber ");
        query.setParameter("cardNumber", cardNumber);
        List cards= query.getResultList();
        return (List<Card>) cards;
    }
    public List<CardDTO> findAllByDepositId(Integer depositId) {
        Query query = getEntityManager().createQuery("SELECT a FROM Card a WHERE a.depositId = :depositId");
        query.setParameter("depositId", depositId);
        return query.getResultList();
    }
}