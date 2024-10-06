package Repository;


import entity.Address;


import java.util.List;

public interface AddressRepository {
    List<Address> findByCityAndCountry(String city, String country);
    List<Address> findByPostalCode(String postalCode);
//    void save(Address address);
//
//    Address findById(int id);
//
//    void deleteById(int id);
//
//    List<Address> findAll();
//
//    void update(Address address);
}