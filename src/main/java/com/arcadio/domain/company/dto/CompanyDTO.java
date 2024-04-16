package com.arcadio.domain.company.dto;

import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.user.userDetails.dto.UserDto;
import com.arcadio.domain.user.userDetails.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
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
    private Set<UserDto> responsiblePerson;
}
