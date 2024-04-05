package com.arcadio.domain.adresses.shippingaddress.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingAddressDTO {
    private String streetName;
    private String buildingNumber;
    private String apartmentNumber;
    private String city;
    private String postalCode;
}
