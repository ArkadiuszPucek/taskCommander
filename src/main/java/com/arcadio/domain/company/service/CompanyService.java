package com.arcadio.domain.company.service;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.company.CompanyFactory;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyFactory companyFactory;

    public CompanyService(CompanyRepository companyRepository, CompanyFactory companyFactory) {
        this.companyRepository = companyRepository;
        this.companyFactory = companyFactory;
    }

    public boolean existsByNip(Long nip) {
        return companyRepository.existsByNip(nip);
    }

    public void addCompany(CompanyDTO company) {
        Company createdCompany = companyFactory.createCompany(company);
        companyRepository.save(createdCompany);
    }


    public void addShippingAddressToCompany(Long nip, ShippingAddressDTO shippingAddress) {
        Company company = companyRepository.findByNip(nip);
        if (company != null) {
            ShippingAddress newShippingAddress = new ShippingAddress();
            newShippingAddress.setStreetName(shippingAddress.getStreetName());
            newShippingAddress.setBuildingNumber(shippingAddress.getBuildingNumber());
            newShippingAddress.setApartmentNumber(shippingAddress.getApartmentNumber());
            newShippingAddress.setCity(shippingAddress.getCity());
            newShippingAddress.setPostalCode(shippingAddress.getPostalCode());
            company.getShippingAddresses().add(newShippingAddress);
            companyRepository.save(company);
        }
    }

    public CompanyDTO getCompanyByNip(Long nip) {
        Company company = companyRepository.findByNip(nip);
        if (company != null) {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setNip(company.getNip());
            companyDTO.setCompanyName(company.getCompanyName());
            return companyDTO;
        }
        return null;
    }
}
