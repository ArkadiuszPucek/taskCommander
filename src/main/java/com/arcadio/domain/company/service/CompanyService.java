package com.arcadio.domain.company.service;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.adresses.shippingaddress.service.ShippingAddressService;
import com.arcadio.domain.company.CompanyFactory;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.dto.CompanyMapper;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.company.repository.CompanyRepository;
import com.arcadio.domain.exceptions.CompanyNotFoundException;
import com.arcadio.domain.exceptions.NoAddressesFoundException;
import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userDetails.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ShippingAddressService shippingAddressService;
    private final CompanyFactory companyFactory;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository, ShippingAddressService shippingAddressService, CompanyFactory companyFactory, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.shippingAddressService = shippingAddressService;
        this.companyFactory = companyFactory;
        this.userRepository = userRepository;
    }


    public boolean existsByNip(Long nip) {
        return companyRepository.existsByNip(nip);
    }

    public Company addCompany(CompanyDTO company) {
        Company createdCompany = companyFactory.createCompany(company);
        companyRepository.save(createdCompany);
        return createdCompany;
    }


    public void addShippingAddressToCompany(Long nip, ShippingAddressDTO shippingAddress) {
        Optional<Company> optionalCompany = companyRepository.findByNip(nip);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            ShippingAddress createdAddress = shippingAddressService.createShippingAddress(shippingAddress, company);
            company.getShippingAddresses().add(createdAddress);
            companyRepository.save(company);
        } else {
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
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            return shippingAddressService.getAllShippingAddressesForCompany(company);
        } else throw new NoAddressesFoundException("No addresses found");
    }

    public Company updateCompany(CompanyDTO companyToUpdate) {
        Company company = companyRepository.findByNip(companyToUpdate.getNip()).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        CompanyMapper.mapToCompany(companyToUpdate, company);
        companyRepository.save(company);
        return company;

    }

    public String getResponsiblePersonsToString(Long nip) {
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
            stringBuilder.append(user.getFirstName() + " " + user.getLastName() + " (" + user.getArea() + ")");
        }
        return stringBuilder.toString();
    }

    public Set<User> getResponsiblePersonsForCompany(Long nip) {
        Company company = companyRepository.findByNip(nip).get();
        return company.getResponsiblePerson();
    }

    public Iterable<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public boolean deleteCompany(Long nip) {
        Company company = companyRepository.findByNip(nip).orElseThrow(() -> new CompanyNotFoundException("Nie znaleziono firmy!"));
        if (company != null) {
            companyRepository.delete(company);
            return true;
        }
        return false;
    }

    public List<Company> findCompaniesByResponsiblePerson(User user) {
        List<Company> companies = new ArrayList<>();
        for (Company company : findAllCompanies()) {
            if (company.getResponsiblePerson().contains(user)) {
                companies.add(company);
            }
        }
        return companies;
    }
}
