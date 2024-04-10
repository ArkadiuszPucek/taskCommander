package com.arcadio.domain.adresses.shippingaddress.dto;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;

public class ShippingAddressMapper {

    public static ShippingAddressDTO map(ShippingAddress shippingAddress){
        ShippingAddressDTO shippingAddressDTO = new ShippingAddressDTO();
        shippingAddressDTO.setId(shippingAddress.getId());
        shippingAddressDTO.setStreetName(shippingAddress.getStreetName());
        shippingAddressDTO.setBuildingNumber(shippingAddress.getBuildingNumber());
        shippingAddressDTO.setApartmentNumber(shippingAddress.getApartmentNumber());
        shippingAddressDTO.setCity(shippingAddress.getCity());
        shippingAddressDTO.setPostalCode(shippingAddress.getPostalCode());
        return shippingAddressDTO;
    }
}
