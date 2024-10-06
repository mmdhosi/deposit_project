package service;

import DTO.*;
import Repository.CustomerRepositoryImpl;
import entity.Customer;
import Repository.CustomerRepository;
import entity.Deposit;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Stateless
public class CustomerService {

    @Inject
    CustomerRepositoryImpl customerRepository;

    @Inject
    CustomerMapper customerMapper;

    @Inject
    CustomerDepositService customerDepositService;
    @Inject
    DepositService depositService;


    public CustomerDTO save(CustomerDTO customerDTO) {
          // Convert DTO to Entity
        String customerNumber = generateUniqueCustomerNumber();
        customerDTO.setCustomerNumber(customerNumber);
        customerDTO.setActive(true);
        validateCustomers(customerDTO);  // Validate the entity
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);  // Save the entity
        return customerMapper.toDTO(savedCustomer);  // Convert saved entity back to DTO
    }

//    public Optional<CustomerDTO> findById(Integer id) {
//        Customer customer=customerRepository.findById(id);
//        CustomerDTO customerDTO=customerMapper.toDTO(customer);
//        return Optional.ofNullable(customerDTO);
//    }
    public CustomerDTO findById(Integer id) {
        Customer customer = customerRepository.findById(id);
        return customerMapper.toDTO(customer); // Convert entity to DTO
    }


    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO>customerDTOS = new ArrayList<CustomerDTO>();
        for (Customer c:customers) {
            customerDTOS.add(customerMapper.toDTO(c));
        }
        return customerDTOS;
    }

    public boolean deleteById(Integer id) {
        return customerRepository.deleteById(id);
    }

    @Transactional
    public void delete(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);  // Map DTO to Entity
        customerRepository.delete(customer);  // Delete the entity
    }

    @Transactional
    public void deleteAll(Iterable<CustomerDTO> customerDTOs) {
        Iterable<Customer> customers = StreamSupport.stream(customerDTOs.spliterator(), false)
                .map(customerMapper::toEntity)
                .collect(Collectors.toList());
        customerRepository.deleteAll(customers);  // Delete all entities
    }

    public long count() {
        return customerRepository.count();
    }

    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }

    public List<CustomerDTO> findByCustomerNumber(String customerNumber){
        List<Customer> customers = customerRepository.findByCustomerNumber(customerNumber);
        List<CustomerDTO>customerDTOS = new ArrayList<CustomerDTO>();
        for (Customer c:customers) {
            customerDTOS.add(customerMapper.toDTO(c));
        }
        return customerDTOS;
    }

    public List<CustomerDTO> findByPhoneNumber(String phone){
        List<Customer> customers = customerRepository.findByPhoneNumber(phone);
        List<CustomerDTO>customerDTOS = new ArrayList<CustomerDTO>();
        for (Customer c:customers) {
            customerDTOS.add(customerMapper.toDTO(c));
        }
        return customerDTOS;
    }

    public List<CustomerDTO> findByEmail(String email){
        List<Customer> customers = customerRepository.findByEmail(email);
        List<CustomerDTO>customerDTOS = new ArrayList<CustomerDTO>();
        for (Customer c:customers) {
            customerDTOS.add(customerMapper.toDTO(c));
        }
        return customerDTOS;
    }

    public List<DepositDTO> findDeposits(Integer id){
        List<DepositDTO> depositDTOS =customerDepositService.findDepositsByCustomerId(id);
        return depositDTOS;
    }
    public List<DepositDTO> findDepositsByCustomerNumber(String customerNumber){
        return findDeposits(customerRepository.findByCustomerNumber(customerNumber).get(0).getCustomerId());
    }

    public List<CardDTO> findCards(Integer id){
        List<DepositDTO> depositDTOS=findDeposits(id);
        List<CardDTO> cardDTOS=new ArrayList<>();
        for (DepositDTO d:depositDTOS) {
            cardDTOS.addAll( depositService.findCards(d.getDepositId()));
        }
        return cardDTOS;
    }


    private void validateCustomers(CustomerDTO customer) {
        if (customer.getCustomerNumber() == null) {
            throw new IllegalArgumentException("Customer number cannot be null or empty");
        }
        if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (customer.getPhone() == null || !isValidPhoneNumber(customer.getPhone())) {
            throw new IllegalArgumentException("Phone number should be valid");
        }

        // Validate date of birth
        if (customer.getDateOfBirth() == null || !isDateOfBirthInPast(customer.getDateOfBirth())) {
            throw new IllegalArgumentException("Date of Birth must be in the past");
        }

        // Validate version
//        if (customer.getVersion() == null || customer.getVersion() < 1) {
//            throw new IllegalArgumentException("Version must be greater than 0");
//        }
        if (!checkCustomerNumberUniqueness(customer.getCustomerNumber())) {
            throw new IllegalArgumentException("Customer number already exists");
        }
        if (!checkPhoneNumberUniqueness(customer.getPhone())) {
            throw new IllegalArgumentException("Phone number already exists");
        }
        if (!checkEmailUniqueness(customer.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    private boolean isValidPhoneNumber(String phone) {
        String phonePattern = "^\\+?[0-9. ()-]{7,}$";
        return Pattern.matches(phonePattern, phone);
    }

    private boolean isDateOfBirthInPast(LocalDateTime dateOfBirth) {
        return dateOfBirth.isBefore(LocalDateTime.now());
    }

    private boolean checkCustomerNumberUniqueness(String customerNumber) {
        List<CustomerDTO> existingCustomers = findByCustomerNumber(customerNumber);
        return existingCustomers.isEmpty();
    }

    private boolean checkPhoneNumberUniqueness(String phone) {
        List<CustomerDTO> existingCustomers = findByPhoneNumber(phone);
        return existingCustomers.isEmpty();
    }

    private boolean checkEmailUniqueness(String email) {
        List<CustomerDTO> existingCustomers = findByEmail(email);
        return existingCustomers.isEmpty();
    }
    private String generateUniqueCustomerNumber() {
        String customerNumber;
        do {
            customerNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 10); // Shorter version
        } while (customerRepository.findByCustomerNumber(customerNumber).size()!=0); // Check for uniqueness
        return customerNumber;
    }
}