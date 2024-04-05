package com.arcadio.domain.company.service;

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
}
