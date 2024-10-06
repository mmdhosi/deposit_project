package DTO;


import entity.Customer;
import DTO.CustomerDTO;

public class CustomerMapper {

    // Method to convert entity to DTO
    public CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getCustomerNumber(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getDateOfBirth(),
                customer.getVersion(),
                customer.getActive()
        );
    }

    // Method to convert DTO to entity
    public Customer toEntity(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }

        return new Customer(
                customerDTO.getCustomerNumber(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getEmail(),
                customerDTO.getPhone(),
                customerDTO.getDateOfBirth(),
                customerDTO.getVersion(),
                customerDTO.getActive()
        );
    }
}