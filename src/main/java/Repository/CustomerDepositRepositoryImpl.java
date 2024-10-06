package Repository;

import entity.Address;
import entity.CustomerDeposit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

@ApplicationScoped
public class CustomerDepositRepositoryImpl extends JpaRepositoryImpl<CustomerDeposit,Integer> implements CustomerDepositRepository{

    public CustomerDepositRepositoryImpl() {

    }

    public CustomerDepositRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public List<CustomerDeposit> findAllByCustomerId(Integer customerId) {
        Query query = getEntityManager().createQuery("SELECT a FROM CustomerDeposit a WHERE a.customerId = :customerId");
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
    public List<CustomerDeposit> findAllByDepositId(Integer depositId) {
        Query query = getEntityManager().createQuery("SELECT a FROM CustomerDeposit a WHERE a.depositId = :depositId");
        query.setParameter("depositId", depositId);
        return query.getResultList();
    }


}
