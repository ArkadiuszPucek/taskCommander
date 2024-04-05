package com.arcadio.domain.company;

import com.arcadio.domain.adresses.billingAddress.BillingAddress;
import com.arcadio.domain.adresses.billingAddress.dto.BillingAddressDTO;
import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.model.Company;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

@Component
public class CompanyFactory {
    public Company createCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        updateCompanyWithDto(company, companyDTO);
        return company;
    }
    private void updateCompanyWithDto(Company company, CompanyDTO companyDTO) {
        company.setNip(companyDTO.getNip());
        company.setCompanyName(companyDTO.getCompanyName());
        company.setShippingAddresses(convertShippingAddresses(companyDTO.getShippingAddresses()));
        company.setBillingAddresses(convertBillingAddresses(companyDTO.getBillingAddresses()));
        company.setCurrency(Currency.getInstance(companyDTO.getCurrency()));
        company.setAdditionalNotes(companyDTO.getAdditionalNotes());
    }

    private Set<ShippingAddress> convertShippingAddresses(Set<ShippingAddressDTO> shippingAddressDTOs) {
        Set<ShippingAddress> shippingAddresses = new HashSet<>();
        if (shippingAddressDTOs != null) {
            for (ShippingAddressDTO dto : shippingAddressDTOs) {
                ShippingAddress shippingAddress = new ShippingAddress();
                shippingAddress.setStreetName(dto.getStreetName());
                shippingAddress.setBuildingNumber(dto.getBuildingNumber());
                shippingAddress.setApartmentNumber(dto.getApartmentNumber());
                shippingAddress.setCity(dto.getCity());
                shippingAddress.setPostalCode(dto.getPostalCode());
                shippingAddresses.add(shippingAddress);
            }
        }
        return shippingAddresses;
    }

    private Set<BillingAddress> convertBillingAddresses(Set<BillingAddressDTO> billingAddressDTOs) {
        Set<BillingAddress> billingAddresses = new HashSet<>();
        if (billingAddressDTOs != null) {
            for (BillingAddressDTO dto : billingAddressDTOs) {
                BillingAddress billingAddress = new BillingAddress();
                billingAddress.setStreetName(dto.getStreetName());
                billingAddress.setBuildingNumber(dto.getBuildingNumber());
                billingAddress.setApartmentNumber(dto.getApartmentNumber());
                billingAddress.setCity(dto.getCity());
                billingAddress.setPostalCode(dto.getPostalCode());
                billingAddresses.add(billingAddress);
            }
        }
        return billingAddresses;
    }

}
