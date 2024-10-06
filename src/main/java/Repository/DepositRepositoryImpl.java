package Repository;

import entity.Customer;
import entity.Deposit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.math.BigDecimal;
import java.util.List;

public class DepositRepositoryImpl extends JpaRepositoryImpl<Deposit,Integer> implements DepositRepository {

    public DepositRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public DepositRepositoryImpl() {
    }
    public List<Deposit> findByDepositNumber(String depositNumber){
        Query query =  getEntityManager().createQuery("SELECT a FROM Deposit a WHERE a.depositNumber = :depositNumber ");
        query.setParameter("depositNumber", depositNumber);
        List deposits= query.getResultList();
        if (deposits.size()==0){
            throw new IllegalArgumentException("deposit not found");
        }
        return (List<Deposit>) deposits;
    }

}