package com.arcadio.domain.adresses.shippingaddress;

import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.company.model.Company;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressFactory {

    public ShippingAddress createShippingAddress(ShippingAddressDTO shippingAddressDTO, Company company) {
        if (shippingAddressDTO == null || company == null) {
            throw new IllegalArgumentException("ShippingAddressDTO and Company cannot be null");
        }
        ShippingAddress shippingAddress = new ShippingAddress();
        mapShippingAddress(shippingAddressDTO, shippingAddress);
        shippingAddress.setCompany(company);
        return shippingAddress;
    }

    public void mapShippingAddress(ShippingAddressDTO shippingAddressDTO, ShippingAddress shippingAddress) {
        if (shippingAddressDTO == null || shippingAddress == null) {
            throw new IllegalArgumentException("ShippingAddressDTO and Company cannot be null");
        }
        shippingAddress.setStreetName(shippingAddressDTO.getStreetName());
        shippingAddress.setBuildingNumber(shippingAddressDTO.getBuildingNumber());
        shippingAddress.setApartmentNumber(shippingAddressDTO.getApartmentNumber());
        shippingAddress.setCity(shippingAddressDTO.getCity());
        shippingAddress.setPostalCode(shippingAddressDTO.getPostalCode());
    }
}
