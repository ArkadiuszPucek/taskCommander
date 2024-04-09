package com.arcadio.domain.company.repository;

import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    boolean existsByNip(Long nip);
    Optional<Company> findByNip(Long nip);
}
