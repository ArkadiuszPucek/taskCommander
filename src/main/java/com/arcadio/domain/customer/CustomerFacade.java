package com.arcadio.domain.customer;

import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.customer.dto.CustomerDTO;
import com.arcadio.domain.customer.model.Customer;
import com.arcadio.domain.customer.service.CustomerService;
import org.springframework.stereotype.Component;

@Component
public class CustomerFacade {
    private final CustomerService customerService;

    public CustomerFacade(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean doesCustomerExists(CustomerDTO customer) {
        return customerService.doesCustomerExists(customer);
    }

    public Customer addCustomer(CustomerDTO customer, Company company) {
        return customerService.addCustomer(customer, company);
    }

    public boolean deleteCustomer(Long customerId) {
        return customerService.deleteCustomer(customerId);
    }

    public Customer getCustomerById(Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    public boolean updateCustomer(CustomerDTO customerToUpdate) {
        return customerService.updateCustomer(customerToUpdate);
    }
}
