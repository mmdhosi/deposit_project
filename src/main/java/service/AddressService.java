package service;

import DTO.AddressDTO;
import DTO.AddressMapper;
import DTO.CustomerDTO;
import Repository.AddressRepositoryImpl;
import entity.Address;
import Repository.AddressRepository;
import entity.Customer;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Stateless
public class AddressService {

    @Inject
    private AddressRepositoryImpl addressRepository;
    @Inject
    private CustomerService customerService;



    @Inject
    private AddressMapper addressMapper;  // Assuming AddressMapper is available as a bean

    public AddressDTO createAddress(AddressDTO addressDTO) {
        addressDTO.setActive(true);
        addressDTO.setExpired(false);
        validateAddress(addressDTO);  // Validate the DTO
        Address address = addressMapper.toEntity(addressDTO);  // Convert DTO to Entity
        Address savedAddress = addressRepository.save(address);  // Save the entity
        return addressMapper.toDTO(savedAddress);  // Convert saved entity back to DTO
    }

    public Optional<AddressDTO> findAddressById(Integer id) {
        Address address = addressRepository.findById(id);
        return Optional.ofNullable(addressMapper.toDTO(address));  // Map to DTO and handle null
    }

    public List<AddressDTO> findAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO>addressDTOS = new ArrayList<AddressDTO>();
        for (Address a:addresses) {
            addressDTOS.add(addressMapper.toDTO(a));
        }
        return addressDTOS;
    }

    public boolean deleteAddress(Integer id) {
        return addressRepository.deleteById(id);
    }

    public void updateAddress(AddressDTO addressDTO) {
        validateAddress(addressDTO);  // Validate the DTO
        Address address = addressMapper.toEntity(addressDTO);  // Convert DTO to Entity
        addressRepository.save(address);  // Save the updated entity
    }

    public List<AddressDTO> findByCityAndCountry(String city, String country) {
        List<Address> addresses = addressRepository.findByCityAndCountry(city, country);
        List<AddressDTO>addressDTOS = new ArrayList<AddressDTO>();
        for (Address a:addresses) {
            addressDTOS.add(addressMapper.toDTO(a));
        }
        return addressDTOS;
    }

    public List<AddressDTO> findByPostalCode(String postalCode) {
        List<Address> addresses = addressRepository.findByPostalCode(postalCode);
        List<AddressDTO>addressDTOS = new ArrayList<AddressDTO>();
        for (Address a:addresses) {
            addressDTOS.add(addressMapper.toDTO(a));
        }
        return addressDTOS;
    }

    public long countAddresses() {
        return addressRepository.count();
    }

    public boolean existsAddressById(Integer id) {
        return addressRepository.existsById(id);
    }

    private void validateAddress(AddressDTO addressDTO) {
        // Validation logic for AddressDTO fields
        if (addressDTO.getPostalCode() == null || addressDTO.getPostalCode().isEmpty()) {
            throw new IllegalArgumentException("Postal code cannot be null or empty");
        }

        if (addressDTO.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null in Address");
        }
        if (!checkPostalCodeUniqueness(addressDTO.getPostalCode())) {
            throw new IllegalArgumentException("postal code already exists");
        }
        if(checkCustomerId(addressDTO.getCustomerId())){
            throw new IllegalArgumentException("customer does not exist");
        }

    }
    private Boolean checkPostalCodeUniqueness(String postalCode){
        return addressRepository.findByPostalCode(postalCode).size() == 0;
    }
    private Boolean checkCustomerId(Integer customerId){
        return customerService.findById(customerId)==null;
    }
}