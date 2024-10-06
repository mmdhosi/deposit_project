package service;

import DTO.CustomerDTO;
import DTO.DepositDTO;
import Repository.CustomerDepositRepositoryImpl;
import entity.CustomerDeposit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class CustomerDepositService {

    @Inject
    private CustomerDepositRepositoryImpl customerDepositRepository;
    @Inject
    private DepositService depositService;
    @Inject CustomerService customerService;

    public CustomerDeposit createCustomerDeposit(CustomerDeposit customerDeposit) {
        validateCustomerDeposit(customerDeposit);
        return customerDepositRepository.save(customerDeposit);
    }

    public CustomerDeposit findCustomerDepositById(Integer id) {
        return customerDepositRepository.findById(id);
    }

    public List<CustomerDeposit> findAllCustomerDeposits() {
        return customerDepositRepository.findAll();
    }

    public boolean deleteCustomerDeposit(Integer id) {
        return customerDepositRepository.deleteById(id);
    }
    public List<DepositDTO> findDepositsByCustomerId(Integer cusromerId){
        List<CustomerDeposit> customerDeposits=findAllByCustomerId(cusromerId);
        List<DepositDTO> depositDTOS=new ArrayList<DepositDTO>();
        for (CustomerDeposit c:customerDeposits) {
            depositDTOS.add(findByDepositId(c.getDepositId()));
        }
        return depositDTOS;
    }
    public List<CustomerDTO> findCustomersByDepositId(Integer depositId){
        List<CustomerDeposit> customerDeposits=findAllByDepositId(depositId);
        List<CustomerDTO> customerDTOS=new ArrayList<CustomerDTO>();
        for (CustomerDeposit c:customerDeposits) {
            customerDTOS.add(findByCustomerId(c.getCustomerId()));
        }
        return customerDTOS;
    }

    public void updateCustomerDeposit(CustomerDeposit customerDeposit) {
        customerDepositRepository.save(customerDeposit);
    }
    public List<CustomerDeposit> findAllByCustomerId(Integer customerId){
        return customerDepositRepository.findAllByCustomerId(customerId);
    }
    public CustomerDTO findByCustomerId(Integer customerId){
        return customerService.findById(customerId);
    }
    public List<CustomerDeposit> findAllByDepositId(Integer depositId){
        return customerDepositRepository.findAllByDepositId(depositId);
    }
    public DepositDTO findByDepositId(Integer depositId){
        return depositService.findDepositById(depositId);
    }

    public long countCustomerDeposits() {
        return customerDepositRepository.count();
    }

    public boolean existsCustomerDepositById(Integer id) {
        return customerDepositRepository.existsById(id);
    }

    private void validateCustomerDeposit(CustomerDeposit customerDeposit){
        if (customerDeposit.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null in CustomerDeposit");
        }
        if (customerDeposit.getDepositId() == null) {
            throw new IllegalArgumentException("Deposit ID cannot be null in CustomerDeposit");
        }
        if (customerDeposit.getAddressId() == null) {
            throw new IllegalArgumentException("Address ID cannot be null in CustomerDeposit");
        }
    }
}