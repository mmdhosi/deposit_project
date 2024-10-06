package Repository;


import Repository.AddressRepository;
import entity.Address;
import entity.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;


public class AddressRepositoryImpl extends JpaRepositoryImpl<Address,Integer> implements AddressRepository {

    public AddressRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public AddressRepositoryImpl() {
    }

    public List<Address> findByCityAndCountry(String city, String country) {

        Query query =  getEntityManager().createQuery("SELECT a FROM Address a WHERE a.city = :city AND a.country = :country");
        query.setParameter("city", city);
        query.setParameter("country", country);
        return query.getResultList();
    }

    public List<Address> findByPostalCode(String postalCode) {
        Query query = getEntityManager().createQuery("SELECT a FROM Address a WHERE a.postalCode = :postalCode");
        query.setParameter("postalCode", postalCode);
        return query.getResultList();
    }

}