package com.arcadio.domain.company;

import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.service.CompanyService;
import org.springframework.stereotype.Component;

@Component
public class CompanyFacade {
    private final CompanyService companyService;

    public CompanyFacade(CompanyService companyService) {
        this.companyService = companyService;
    }

    public boolean doesCompanyExists(Long nip) {
        return companyService.existsByNip(nip);
    }

    public void addCompany(CompanyDTO company) {
        companyService.addCompany(company);
    }

    public CompanyDTO getCompanyByNip(Long nip) {
        return companyService.getCompanyByNip(nip);
    }

    public void addShippingAddressToCompany(Long nip, ShippingAddressDTO shippingAddress) {
        companyService.addShippingAddressToCompany(nip,shippingAddress);
    }
}
