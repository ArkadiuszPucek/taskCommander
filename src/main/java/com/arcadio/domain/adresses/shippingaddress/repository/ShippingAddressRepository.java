package com.arcadio.domain.adresses.shippingaddress.repository;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {
}
