package com.arcadio.domain.company;

import com.arcadio.domain.UserUtils;
import com.arcadio.domain.company.dto.CompanyDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CompanyManagementFacade {
    private final UserUtils userUtils;
    private final CompanyFacade companyFacade;

    public CompanyManagementFacade(UserUtils userUtils, CompanyFacade companyFacade) {
        this.userUtils = userUtils;
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
    public void addCompany(CompanyDTO company) {
        companyFacade.addCompany(company);

    }
}
