package com.arcadio.domain.company.service;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.adresses.shippingaddress.service.ShippingAddressService;
import com.arcadio.domain.company.CompanyFactory;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.company.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ShippingAddressService shippingAddressService;
    private final CompanyFactory companyFactory;

    public CompanyService(CompanyRepository companyRepository, ShippingAddressService shippingAddressService, CompanyFactory companyFactory) {
        this.companyRepository = companyRepository;
        this.shippingAddressService = shippingAddressService;
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
        Optional<Company> optionalCompany = companyRepository.findByNip(nip);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            ShippingAddress createdAddress = shippingAddressService.createShippingAddress(shippingAddress, company);
            company.getShippingAddresses().add(createdAddress);
            companyRepository.save(company);
        } else {
            // Obsługa przypadku, gdy firma nie została znaleziona
            throw new EntityNotFoundException("Company with NIP: " + nip + " not found.");
        }
    }

    public CompanyDTO getCompanyByNip(Long nip) {
        Optional<Company> optionalCompany = companyRepository.findByNip(nip);
        Company company = optionalCompany.get();
        if (optionalCompany.isPresent()) {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setNip(company.getNip());
            companyDTO.setCompanyName(company.getCompanyName());
            return companyDTO;
        }
        return null;
    }
}
