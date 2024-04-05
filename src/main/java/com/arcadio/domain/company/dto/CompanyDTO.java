package com.arcadio.domain.company.dto;

import com.arcadio.domain.adresses.billingAddress.dto.BillingAddressDTO;
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
    private Set<BillingAddressDTO> billingAddresses;
    private String currency;
    private String billingEmail;
    private String additionalNotes;
}
