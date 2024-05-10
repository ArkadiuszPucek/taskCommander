package com.arcadio.domain.customer;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.customer.dto.CustomerDTO;
import com.arcadio.domain.customer.dto.CustomerMapper;
import com.arcadio.domain.customer.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {

    public Customer createCustomer(CustomerDTO customerDTO, Company company) {
        Customer customer = new Customer();
        return CustomerMapper.mapToCustomer(customerDTO, customer, company);
    }

    public void mapCustomer(CustomerDTO customerToUpdate, Customer customer) {
        if (customerToUpdate == null || customer == null) {
            throw new IllegalArgumentException("CustomerDTO and Customer cannot be null");
        }
        customer.setCompany(customerToUpdate.getCompany());
        customer.setFirstName(customerToUpdate.getFirstName());
        customer.setLastName(customerToUpdate.getLastName());
        customer.setEmail(customerToUpdate.getEmail());
        customer.setPhone(customerToUpdate.getPhone());
        customer.setPosition(customerToUpdate.getPosition());
    }
}
