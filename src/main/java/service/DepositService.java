package service;

import DTO.*;
import Repository.DepositRepositoryImpl;
import entity.CustomerDeposit;
import entity.Deposit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class DepositService {

    @Inject
    private DepositRepositoryImpl depositRepository;
    @Inject
    DepositMapper depositMapper;
    @Inject
    private CustomerDepositService customerDepositService;
    @Inject
    private CardService cardService;

    public DepositDTO createDeposit(DepositDTO depositDTO) {
        depositDTO.setDepositNumber(generateUniqueDepositNumber()); // Generate a unique deposit number
        depositDTO.setActive(true);
        depositDTO.setExpired(false);
        validateDeposit(depositDTO); // Validate the DTO
        Deposit deposit = DepositMapper.toEntity(depositDTO); // Convert DTO to Entity
        Deposit savedDeposit = depositRepository.save(deposit); // Save the entity
        CustomerDeposit customerDeposit = new CustomerDeposit(depositDTO.getCustomerId(), findByDepositNumber(savedDeposit.getDepositNumber()).get(0).getDepositId(), depositDTO.getAddressId(),false,true);
        customerDepositService.createCustomerDeposit(customerDeposit);
        return DepositMapper.toDTO(savedDeposit); // Convert saved entity back to DTO
    }

    public DepositDTO findDepositById(Integer id) {
        Deposit deposit = depositRepository.findById(id);
        return DepositMapper.toDTO(deposit); // Convert entity to DTO
    }

    public List<DepositDTO> findAllDeposits() {
        List<Deposit> deposits = depositRepository.findAll();
        List<DepositDTO>depositDTOS = new ArrayList<DepositDTO>();
        for (Deposit d:deposits) {
            depositDTOS.add(depositMapper.toDTO(d));
        }
        return depositDTOS;
    }


    public List<CustomerDTO> findCustomers(Integer depositId){
        return customerDepositService.findCustomersByDepositId(depositId);
    }
    public List<CustomerDTO> findCustomersByDepositNumber(String depositNumber){
        return findCustomers(depositRepository.findByDepositNumber(depositNumber).get(0).getDepositId());
    }

    public List<CardDTO> findCards(Integer id){
        return cardService.findByDepositId(id);
    }
    public boolean deleteDeposit(Integer id) {
        return depositRepository.deleteById(id);
    }

    public DepositDTO updateDeposit(DepositDTO depositDTO) {
        validateDeposit(depositDTO); // Validate the DTO
        Deposit deposit = DepositMapper.toEntity(depositDTO); // Convert DTO to Entity
        Deposit updatedDeposit = depositRepository.save(deposit); // Update the entity
        return DepositMapper.toDTO(updatedDeposit); // Convert saved entity back to DTO
    }

    public long countDeposits() {
        return depositRepository.count();
    }

    public boolean existsDepositById(Integer id) {
        return depositRepository.existsById(id);
    }

    public List<DepositDTO> findByDepositNumber(String depositNumber){
        List<Deposit> deposits=depositRepository.findByDepositNumber(depositNumber);
        List<DepositDTO>depositDTOS = new ArrayList<DepositDTO>();
        for (Deposit d:deposits) {
            depositDTOS.add(depositMapper.toDTO(d));
        }
        return depositDTOS;
    }


    private void validateDeposit(DepositDTO depositDTO) {
        if (depositDTO.getDepositNumber() == null || depositDTO.getDepositNumber().isEmpty()) {
            throw new IllegalArgumentException("Deposit number cannot be null or empty");
        }
        if (depositDTO.getBalance() == null || depositDTO.getBalance() < 0) {
            throw new IllegalArgumentException("Balance must be a non-negative value");
        }
        if (depositDTO.getAccountType() == null) {
            throw new IllegalArgumentException("Account type cannot be null");
        }
//        if (depositDTO.getVersion() == null || depositDTO.getVersion() < 1) {
//            throw new IllegalArgumentException("Deposit version must be greater than 0");
//        }
        if (!checkDepositNumberUniqueness(depositDTO.getDepositNumber())) {
            throw new IllegalArgumentException("Deposit number already exists");
        }
    }

    private boolean checkDepositNumberUniqueness(String depositNumber) {
        List<DepositDTO> existingDeposits = findByDepositNumber(depositNumber);
        return existingDeposits.isEmpty();
    }

    private String generateUniqueDepositNumber() {
        String depositNumber;
        do {
            depositNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 10); // Shorter version
        } while (!checkDepositNumberUniqueness(depositNumber)); // Check for uniqueness
        return depositNumber;
    }
}