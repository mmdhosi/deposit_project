package Repository;


import entity.Transaction;
import jakarta.persistence.EntityManager;

public class TransactionRepositoryImpl extends JpaRepositoryImpl<Transaction,Integer> implements TransactionRepository {

    public TransactionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public TransactionRepositoryImpl() {
    }
}