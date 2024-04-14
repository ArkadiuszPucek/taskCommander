package com.arcadio.domain.company.service;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressMapper;
import com.arcadio.domain.adresses.shippingaddress.service.ShippingAddressService;
import com.arcadio.domain.company.CompanyFactory;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.dto.CompanyMapper;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.company.repository.CompanyRepository;
import com.arcadio.domain.exceptions.CompanyNotFoundException;
import com.arcadio.domain.exceptions.NoAddressesFoundException;
import com.arcadio.domain.exceptions.UserNotFoundException;
import com.arcadio.domain.user.userDetails.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public CompanyDTO getCompanyDTOByNip(Long nip) {
        return companyRepository.findByNip(nip)
                .map(CompanyMapper::mapToCompanyDTO)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
    }

    public Company getCompanyByNip(Long nip) {
        Optional<Company> optionalCompany = companyRepository.findByNip(nip);
        return optionalCompany.orElse(null);
    }

    public List<ShippingAddress> getCompanyShippingAddresses(Long nip) {
        Optional<Company> optionalCompany = companyRepository.findByNip(nip);
        if (optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            return shippingAddressService.getAllShippingAddressesForCompany(company);
        }else throw new NoAddressesFoundException("No addresses found");
    }

    public boolean updateCompany(CompanyDTO companyToUpdate) {
        Company company = companyRepository.findByNip(companyToUpdate.getNip()).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        if (company != null){
            CompanyMapper.mapToCompany(companyToUpdate, company);
            companyRepository.save(company);
            return true;
        }else {
            return false;
        }
    }

    public String getResponsiblePersons(Long nip) {
        Company company = companyRepository.findByNip(nip).get();
        Set<User> responsiblePerson = company.getResponsiblePerson();
        if (responsiblePerson == null || responsiblePerson.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : responsiblePerson) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(user.getFirstName() + " " + user.getLastName() + " ("+user.getArea()+")");
        }
        return stringBuilder.toString();
    }
}
