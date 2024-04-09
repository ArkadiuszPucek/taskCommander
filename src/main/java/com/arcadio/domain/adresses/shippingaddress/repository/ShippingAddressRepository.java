package com.arcadio.domain.adresses.shippingaddress.repository;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.company.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {
    List<ShippingAddress> findAllByCompany(Company company);
}
