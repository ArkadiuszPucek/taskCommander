package com.arcadio.domain.company;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.adresses.shippingaddress.service.ShippingAddressService;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.dto.CompanyMapper;
import com.arcadio.domain.company.model.Company;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class CompanyFactory {
        public Company createCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        return CompanyMapper.mapToCompany(companyDTO, company);

    }
}
