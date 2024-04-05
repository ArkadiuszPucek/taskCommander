package com.arcadio.domain.company.dto;

import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CompanyDTO {
    private Long nip;
    private String companyName;
    private Set<ShippingAddressDTO> shippingAddresses;
    private String currency;
    private String billingMail;
    private String billingStreetName;
    private String billingBuildingNumber;
    private String billingApartmentNumber;
    private String billingCity;
    private String billingPostalCode;
    private String additionalNotes;
}
