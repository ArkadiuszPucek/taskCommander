package com.arcadio.domain.company;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.adresses.shippingaddress.service.ShippingAddressService;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.company.service.CompanyService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyFacade {
    private final CompanyService companyService;
    private final ShippingAddressService shippingAddressService;

    public CompanyFacade(CompanyService companyService, ShippingAddressService shippingAddressService) {
        this.companyService = companyService;
        this.shippingAddressService = shippingAddressService;
    }

    public boolean doesCompanyExists(Long nip) {
        return companyService.existsByNip(nip);
    }

    public Company addCompany(CompanyDTO company) {
        return companyService.addCompany(company);
    }

    public CompanyDTO getCompanyDTOByNip(Long nip) {
        return companyService.getCompanyDTOByNip(nip);
    }

    public Company getCompanyByNip(Long nip) {
        return companyService.getCompanyByNip(nip);
    }

    public void addShippingAddressToCompany(Long nip, ShippingAddressDTO shippingAddress) {
        companyService.addShippingAddressToCompany(nip,shippingAddress);
    }

    public List<ShippingAddress> getCompanyShippingAddresses(Long nip) {
        return companyService.getCompanyShippingAddresses(nip);
    }

    public void deleteShippingAddressFromCompany(Long shippingAddress) {
        shippingAddressService.deleteShippingAddress(shippingAddress);
    }

    public boolean updateShippingAddress(ShippingAddressDTO shippingAddressDto) {
        return shippingAddressService.updateShippingAddress(shippingAddressDto);
    }

    public ShippingAddressDTO getCompanyShippingAddressById(Long shippingAddress) {
        return shippingAddressService.getCompanyShippingAddressById(shippingAddress);
    }

    public boolean updateCompany(CompanyDTO companyToUpdate) {
        return companyService.updateCompany(companyToUpdate);
    }

    public String getResponsiblePersons(Long nip) {
        return companyService.getResponsiblePersons(nip);
    }
}
