package com.arcadio.domain.customer.repository;

import com.arcadio.domain.customer.model.Customer;
import com.arcadio.domain.user.userDetails.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByFirstNameAndLastNameAndPhoneAndEmail(String firstName, String lastName, String phone, String email);
}
