package com.arcadio.domain.customer;

import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.customer.dto.CustomerDTO;
import com.arcadio.domain.customer.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerManagementFacade {

    private final CustomerFacade customerFacade;

    public CustomerManagementFacade(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    public boolean doesCustomerExists(CustomerDTO customer) {
        return customerFacade.doesCustomerExists(customer);
    }

    public Customer addCustomer(CustomerDTO customer, Company company) {
        return customerFacade.addCustomer(customer, company);
    }

    public boolean deleteCustomer(Long customerId) {
        return customerFacade.deleteCustomer(customerId);
    }

    public Customer getCustomerById(Long customerId) {
        return customerFacade.getCustomerById(customerId);
    }

    public boolean updateCustomer(CustomerDTO customerToUpdate) {
        return customerFacade.updateCustomer(customerToUpdate);
    }
}
