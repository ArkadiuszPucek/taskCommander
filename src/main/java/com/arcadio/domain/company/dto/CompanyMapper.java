package com.arcadio.domain.company.dto;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.adresses.shippingaddress.service.ShippingAddressService;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.user.userDetails.dto.UserDto;
import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userRole.model.UserRole;

import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

public class CompanyMapper {

    public static CompanyDTO mapToCompanyDTO(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setNip(company.getNip());
        companyDTO.setCompanyName(company.getCompanyName());
        companyDTO.setCurrency(company.getCurrency().toString());
        companyDTO.setBillingMail(company.getBillingMail());
        companyDTO.setBillingStreetName(company.getBillingStreetName());
        companyDTO.setBillingBuildingNumber(company.getBillingBuildingNumber());
        companyDTO.setBillingApartmentNumber(company.getBillingApartmentNumber());
        companyDTO.setBillingCity(company.getBillingCity());
        companyDTO.setBillingPostalCode(company.getBillingPostalCode());
        companyDTO.setAdditionalNotes(company.getAdditionalNotes());

        Set<ShippingAddressDTO> shippingAddressesDTO = new HashSet<>();
        for (ShippingAddress shippingAddress : company.getShippingAddresses()) {
            ShippingAddressDTO shippingAddressDTO = new ShippingAddressDTO();
            shippingAddressDTO.setStreetName(shippingAddress.getStreetName());
            shippingAddressDTO.setBuildingNumber(shippingAddress.getBuildingNumber());
            shippingAddressDTO.setApartmentNumber(shippingAddress.getApartmentNumber());
            shippingAddressDTO.setCity(shippingAddress.getCity());
            shippingAddressDTO.setPostalCode(shippingAddress.getPostalCode());
            shippingAddressesDTO.add(shippingAddressDTO);
        }
        companyDTO.setShippingAddresses(shippingAddressesDTO);

        Set<UserDto> responsiblePersonsDTO = new HashSet<>();
        for (User user : company.getResponsiblePerson()) {
            UserDto userDTO = new UserDto();
            userDTO.setId(user.getId());
            userDTO.setArea(user.getArea());
            responsiblePersonsDTO.add(userDTO);
        }
        companyDTO.setResponsiblePerson(responsiblePersonsDTO);

        return companyDTO;
    }

    public static Company mapToCompany(CompanyDTO companyDTO, Company company) {
        if (companyDTO == null || company == null) {
            throw new IllegalArgumentException("CompanyDTO and Company cannot be null");
        }
        company.setNip(companyDTO.getNip());
        company.setCompanyName(companyDTO.getCompanyName());
//        company.setShippingAddresses(convertShippingAddresses(companyDTO.getShippingAddresses()));
        company.setCurrency(Currency.getInstance(companyDTO.getCurrency()));
        company.setBillingMail(companyDTO.getBillingMail());
        company.setBillingStreetName(companyDTO.getBillingStreetName());
        company.setBillingBuildingNumber(companyDTO.getBillingBuildingNumber());
        company.setBillingApartmentNumber(companyDTO.getBillingApartmentNumber());
        company.setBillingCity(companyDTO.getBillingCity());
        company.setBillingPostalCode(companyDTO.getBillingPostalCode());
        company.setAdditionalNotes(companyDTO.getAdditionalNotes());

        Set<User> responsiblePersons = new HashSet<>();
        for (UserDto userDTO : companyDTO.getResponsiblePerson()) {
            User user = new User();
            user.setId(userDTO.getId());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setArea(userDTO.getArea());
            UserRole role = new UserRole();
            role.setName(userDTO.getRole().getName());
            user.setRoles(Collections.singleton(role));
            responsiblePersons.add(user);
        }
        company.setResponsiblePerson(responsiblePersons);
        return company;
    }

    private static Set<ShippingAddress> convertShippingAddresses(Set<ShippingAddressDTO> shippingAddressDTOs) {
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
