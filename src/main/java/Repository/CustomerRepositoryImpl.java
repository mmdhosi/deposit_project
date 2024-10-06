package Repository;
import entity.Card;
import entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CustomerRepositoryImpl extends JpaRepositoryImpl<Customer,Integer> implements CustomerRepository {

    public CustomerRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public CustomerRepositoryImpl() {
    }
    public List<Customer> findByCustomerNumber(String customerNumber){
        Query query =  getEntityManager().createQuery("SELECT a FROM Customer a WHERE a.customerNumber = :customerNumber ");
        query.setParameter("customerNumber", customerNumber);
        List customers= query.getResultList();
        if (customers.size()==0){
            throw new IllegalArgumentException("Customer not found");
        }
        return (List<Customer>) customers;

    }
    public List<Customer> findByPhoneNumber(String phone){
        Query query =  getEntityManager().createQuery("SELECT a FROM Customer a WHERE a.phone = :phone ");
        query.setParameter("phone", phone);
        List customers= query.getResultList();
        return (List<Customer>) customers;

    }
    public List<Customer> findByEmail(String email){
        Query query =  getEntityManager().createQuery("SELECT a FROM Customer a WHERE a.email = :email ");
        query.setParameter("email", email);
        List customers= query.getResultList();
        return (List<Customer>) customers;

    }

}