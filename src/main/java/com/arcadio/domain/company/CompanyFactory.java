package com.arcadio.domain.company;

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
        company.setCurrency(Currency.getInstance(companyDTO.getCurrency()));
        company.setBillingMail(companyDTO.getBillingMail());
        company.setBillingStreetName(companyDTO.getBillingStreetName());
        company.setBillingBuildingNumber(companyDTO.getBillingBuildingNumber());
        company.setBillingApartmentNumber(companyDTO.getBillingApartmentNumber());
        company.setBillingCity(companyDTO.getBillingCity());
        company.setBillingPostalCode(companyDTO.getBillingPostalCode());
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
}
