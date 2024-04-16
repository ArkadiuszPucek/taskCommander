package com.arcadio.domain.company;

import com.arcadio.domain.UserUtils;
import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.user.UserFacade;
import com.arcadio.domain.user.userDetails.dto.UserDto;
import com.arcadio.domain.user.userDetails.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

@Service
public class CompanyManagementFacade {
    private final UserUtils userUtils;
    private final UserFacade userFacade;
    private final CompanyFacade companyFacade;

    public CompanyManagementFacade(UserUtils userUtils, UserFacade userFacade, CompanyFacade companyFacade) {
        this.userUtils = userUtils;
        this.userFacade = userFacade;
        this.companyFacade = companyFacade;
    }

    public void addAvatarUrlToModel(Authentication authentication, Model model) {
        userUtils.addAvatarUrlToModel(authentication, model);

    }public void addUserEmailToModel(Authentication authentication, Model model) {
        userUtils.getUserEmailToModel(authentication, model);

    }public void addUserRoleToModel(Authentication authentication, Model model) {
        userUtils.getUserRoleToModel(authentication, model);
    }
    public boolean doesCompanyExists(Long nip) {
        return companyFacade.doesCompanyExists(nip);
    }
    public Company addCompany(CompanyDTO company) {
        return companyFacade.addCompany(company);
    }
    public CompanyDTO getCompanyDTOByNip(Long nip) {
        return companyFacade.getCompanyDTOByNip(nip);
    }

    public Company getCompanyByNip(Long nip) {
        return companyFacade.getCompanyByNip(nip);
    }

    public void addShippingAddressToCompany(Long nip, ShippingAddressDTO shippingAddress) {
        companyFacade.addShippingAddressToCompany(nip, shippingAddress);
    }

    public List<ShippingAddress> getCompanyShippingAddresses(Long nip) {
        return companyFacade.getCompanyShippingAddresses(nip);
    }

    public void deleteShippingAddressFromCompany(Long shippingAddress) {
        companyFacade.deleteShippingAddressFromCompany(shippingAddress);
    }

    public boolean updateShippingAddress(ShippingAddressDTO shippingAddressDto) {
        return companyFacade.updateShippingAddress(shippingAddressDto);
    }

    public ShippingAddressDTO getCompanyShippingAddressById(Long shippingAddress) {
        return companyFacade.getCompanyShippingAddressById(shippingAddress);
    }

    public boolean updateCompany(CompanyDTO companyToUpdate) {
        return companyFacade.updateCompany(companyToUpdate);
    }

    public String getResponsiblePersons(Long nip) {
        return companyFacade.getResponsiblePersons(nip);
    }

    public List<User> getUsersByRole(String userRole) {
        return userFacade.getUsersByRole(userRole);
    }

    public Set<UserDto> findUsersByIds(List<Long> responsiblePersonIds) {
        return userFacade.findUsersByIds(responsiblePersonIds);
    }

    public void addCompanyToUser(UserDto userDto, Company company) {
            userFacade.addCompanyToUser(userDto, company);
    }
}
