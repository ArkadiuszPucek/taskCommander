package com.arcadio.domain.customer.dto;

import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.customer.model.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer, Company company) {
        if (customerDTO == null || customer == null || company == null){
            throw new IllegalArgumentException("CustomerDTO, Customer or Company cannot be null!");
        }
        customer.setCompany(company);
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setPosition(customerDTO.getPosition());
        return customer;
    }
}
