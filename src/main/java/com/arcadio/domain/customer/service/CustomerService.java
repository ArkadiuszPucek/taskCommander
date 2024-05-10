package com.arcadio.domain.customer.service;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.customer.CustomerFactory;
import com.arcadio.domain.customer.dto.CustomerDTO;
import com.arcadio.domain.customer.model.Customer;
import com.arcadio.domain.customer.repository.CustomerRepository;
import com.arcadio.domain.exceptions.CompanyNotFoundException;
import com.arcadio.domain.exceptions.CustomerNotFoundException;
import com.arcadio.domain.exceptions.NoAddressesFoundException;
import com.arcadio.domain.user.userDetails.model.User;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerFactory customerFactory;

    public CustomerService(CustomerRepository customerRepository, CustomerFactory customerFactory) {
        this.customerRepository = customerRepository;
        this.customerFactory = customerFactory;
    }

    public boolean doesCustomerExists(CustomerDTO customer) {
        return customerRepository.findByFirstNameAndLastNameAndPhoneAndEmail(customer.getFirstName(),
                customer.getLastName() , customer.getPhone() , customer.getEmail()) != null;
    }

    public Customer addCustomer(CustomerDTO customer, Company company) {
        Customer createdCustomer = customerFactory.createCustomer(customer, company);
        customerRepository.save(createdCustomer);
        return createdCustomer;
    }

    public boolean deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
        if (customer != null) {
            customerRepository.delete(customer);
            return true;
        }
        return false;
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
    }

    public boolean updateCustomer(CustomerDTO customerToUpdate) {
        Customer customer = customerRepository.findById(customerToUpdate.getId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        if (customer != null){
            customerFactory.mapCustomer(customerToUpdate, customer);
            customerRepository.save(customer);
            return true;
        }else {
            return false;
        }
    }
}
