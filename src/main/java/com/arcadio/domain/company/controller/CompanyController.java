package com.arcadio.domain.company.controller;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.company.CompanyManagementFacade;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.model.Company;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyManagementFacade companyManagementFacade;

    public CompanyController(CompanyManagementFacade companyManagementFacade) {
        this.companyManagementFacade = companyManagementFacade;
    }

    @GetMapping("/add-company")
    public String showAddCompanyForm(Authentication authentication, Model model) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        CompanyDTO company = new CompanyDTO();

        model.addAttribute("company", company);

        return "company/add-company-form";
    }

    @PostMapping("/add-company")
    public String addCompanyAndBillingAddress(CompanyDTO company, RedirectAttributes redirectAttributes) {
        if (companyManagementFacade.doesCompanyExists(company.getNip())) {
            redirectAttributes.addFlashAttribute("error", "Company with the given NIP exists on the website!");
            return "redirect:/company/add-company";
        }
        Long nip = company.getNip();
        companyManagementFacade.addCompany(company);

        return "redirect:/company/add-company/" + nip + "/add-shipping-address";
    }

    @GetMapping("/{nip}")
    public String showCompany(@PathVariable Long nip, Authentication authentication, Model model) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        Company companyByNip = companyManagementFacade.getCompanyByNip(nip);
        if (companyByNip == null) {
            return "error/not-found";
        }
        List<ShippingAddress> companyShippingAddresses = companyManagementFacade.getCompanyShippingAddresses(nip);
        model.addAttribute("company", companyByNip);
        model.addAttribute("companyShippingAddresses", companyShippingAddresses);

        return "company/company-preview";
    }

    @GetMapping("/{nip}")
    public String editCompany(@PathVariable Long nip, Authentication authentication, Model model) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        Company companyByNip = companyManagementFacade.getCompanyByNip(nip);
        if (companyByNip == null) {
            return "error/not-found";
        }
        List<ShippingAddress> companyShippingAddresses = companyManagementFacade.getCompanyShippingAddresses(nip);
        model.addAttribute("company", companyByNip);
        model.addAttribute("companyShippingAddresses", companyShippingAddresses);

        return "company/company-edit-form";
    }

    @GetMapping("/add-company/{nip}/add-shipping-address")
    public String showAddShippingAddressForm(@PathVariable Long nip, Model model, Authentication authentication) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);
        CompanyDTO company = companyManagementFacade.getCompanyDTOByNip(nip);
        model.addAttribute("company", company);
        model.addAttribute("shippingAddress", new ShippingAddressDTO());

        return "company/shipping-address-form";
    }

    @PostMapping("/add-company/{nip}/add-shipping-address")
    public String addShippingAddress(@PathVariable Long nip, ShippingAddressDTO shippingAddress) {
        companyManagementFacade.addShippingAddressToCompany(nip, shippingAddress);

        return "redirect:/company/" + nip;
    }
}
