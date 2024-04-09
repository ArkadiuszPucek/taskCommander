package com.arcadio.domain.adresses.shippingaddress.service;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.adresses.shippingaddress.repository.ShippingAddressRepository;
import com.arcadio.domain.company.model.Company;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    public ShippingAddressService(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    public ShippingAddress createShippingAddress(ShippingAddressDTO shippingAddressDTO, Company company) {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setStreetName(shippingAddressDTO.getStreetName());
        shippingAddress.setBuildingNumber(shippingAddressDTO.getBuildingNumber());
        shippingAddress.setApartmentNumber(shippingAddressDTO.getApartmentNumber());
        shippingAddress.setCity(shippingAddressDTO.getCity());
        shippingAddress.setPostalCode(shippingAddressDTO.getPostalCode());
        shippingAddress.setCompany(company);

        return shippingAddressRepository.save(shippingAddress);
    }

}
