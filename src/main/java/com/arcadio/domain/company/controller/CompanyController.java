package com.arcadio.domain.company.controller;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.company.CompanyManagementFacade;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.model.Company;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
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

        return "redirect:/company/add-shipping-address/" + nip;
    }

    @GetMapping("/{nip}")
    public String showCompany(@PathVariable Long nip, Authentication authentication, Model model, HttpSession session) {
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
        session.setAttribute("nip", nip);

        return "company/company-preview";
    }

    @GetMapping("/add-shipping-address/{nip}")
    public String showAddShippingAddressForm(@PathVariable Long nip, Model model, Authentication authentication) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);
        CompanyDTO company = companyManagementFacade.getCompanyDTOByNip(nip);
        model.addAttribute("company", company);
        model.addAttribute("shippingAddress", new ShippingAddressDTO());

        return "company/shipping-address-form";
    }

    @PostMapping("/add-shipping-address/{nip}")
    public String addShippingAddress(@PathVariable Long nip, ShippingAddressDTO shippingAddress) {
        companyManagementFacade.addShippingAddressToCompany(nip, shippingAddress);

        return "redirect:/company/" + nip;
    }

    @GetMapping("/delete-shipping-address/{shippingAddress}")
    public String deleteShippingAddress(@PathVariable Long shippingAddress, HttpSession session) {

        companyManagementFacade.deleteShippingAddressFromCompany(shippingAddress);
        return "redirect:/company/" + session.getAttribute("nip");
    }

    @GetMapping("/update/{nip}")
    public String updateCompany(@PathVariable Long nip, Authentication authentication, Model model) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        CompanyDTO companyByNip = companyManagementFacade.getCompanyDTOByNip(nip);
        model.addAttribute("companyToUpdate", companyByNip);

        return "company/company-edit-form";
    }

    @PostMapping("/update-company")
    public String updateCompany(@ModelAttribute("companyToUpdate") CompanyDTO companyToUpdate, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            boolean updateResult = companyManagementFacade.updateCompany(companyToUpdate);

            if (updateResult) {
                redirectAttributes.addFlashAttribute("success", "The address has been successfully updated.");
                return "redirect:/company/"+ session.getAttribute("nip");
            } else {
                redirectAttributes.addFlashAttribute("error", "Address not found");
                return "redirect:/company/edit-shipping-address/" + companyToUpdate.getNip();
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while updating the address: " + e.getMessage());
            return "redirect:/company/edit-shipping-address/" + companyToUpdate.getNip();
        }
    }


    @GetMapping("/edit-shipping-address/{shippingAddress}")
    public String updateShippingAddress(@PathVariable Long shippingAddress, Authentication authentication, Model model, HttpSession session) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        ShippingAddressDTO companyShippingAddressById = companyManagementFacade.getCompanyShippingAddressById(shippingAddress);
        Company company = companyManagementFacade.getCompanyByNip((Long) session.getAttribute("nip"));
        model.addAttribute("addressToUpdate", companyShippingAddressById);
        model.addAttribute("company", company);
        return "company/edit-shipping-address-form";
    }

    @PostMapping("/edit-shipping-address")
    public String updateShippingAddress(@ModelAttribute("addressToUpdate") ShippingAddressDTO addressToUpdate, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            boolean updateResult = companyManagementFacade.updateShippingAddress(addressToUpdate);

            if (updateResult) {
                redirectAttributes.addFlashAttribute("success", "The address has been successfully updated.");
                return "redirect:/company/"+ session.getAttribute("nip");
            } else {
                redirectAttributes.addFlashAttribute("error", "Address not found");
                return "redirect:/company/edit-shipping-address/" + addressToUpdate.getId();
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while updating the address: " + e.getMessage());
            return "redirect:/company/edit-shipping-address/" + addressToUpdate.getId();
        }
    }
}
