package com.arcadio.domain.company.controller;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.company.CompanyManagementFacade;
import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.user.userDetails.dto.UserDto;
import com.arcadio.domain.user.userDetails.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private static final String SALES_ENGINEER_ROLE = "Sales Engineer";
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
        List<User> salesEngineers = companyManagementFacade.getUsersByRole(SALES_ENGINEER_ROLE);
        model.addAttribute("salesEngineers", salesEngineers);
        model.addAttribute("company", company);

        return "company/add-company-form";
    }

    @PostMapping("/add-company")
    public String addCompany(CompanyDTO company, RedirectAttributes redirectAttributes, @RequestParam("responsiblePersonIds") List<Long> responsiblePersonIds) {
        if (companyManagementFacade.doesCompanyExists(company.getNip())) {
            redirectAttributes.addFlashAttribute("error", "Company with the given NIP exists on the website!");
            return "redirect:/company/add-company";
        }
        Long nip = company.getNip();

        Set<UserDto> usersByIds = companyManagementFacade.findUsersByIds(responsiblePersonIds);
        company.setResponsiblePerson(usersByIds);
        Company createdCompany = companyManagementFacade.addCompany(company);
        for (UserDto userDTO : usersByIds) {
            userDTO.getCompanies().add(createdCompany);
            companyManagementFacade.addCompanyToUser(userDTO, createdCompany);
        }

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
        String responsiblePersons = companyManagementFacade.getResponsiblePersonsToString(nip);
        model.addAttribute("company", companyByNip);
        model.addAttribute("companyShippingAddresses", companyShippingAddresses);
        model.addAttribute("responsiblePersons", responsiblePersons);
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
        Set<User> responsiblePersonsForCompany = companyManagementFacade.getResponsiblePersonsForCompany(nip);
        List<User> salesEngineers = companyManagementFacade.getUsersByRole(SALES_ENGINEER_ROLE);
        model.addAttribute("salesEngineers", salesEngineers);
        model.addAttribute("responsiblePersonsForCompany", responsiblePersonsForCompany);
        model.addAttribute("companyToUpdate", companyByNip);

        return "company/company-edit-form";
    }

    @PostMapping("/update-company")
    public String updateCompany(@ModelAttribute("companyToUpdate") CompanyDTO companyToUpdate,
                                @RequestParam("responsiblePersonIds") List<Long> responsiblePersonIds,
                                RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            Long nip = (Long) session.getAttribute("nip");
            Set<User> oldResponsiblePersons = companyManagementFacade.getResponsiblePersonsForCompany(nip);
            Set<UserDto> newResponsiblePersons = companyManagementFacade.findUsersByIds(responsiblePersonIds);
            oldResponsiblePersons.forEach(oldUser -> companyManagementFacade.removeCompanyFromUser(oldUser, companyToUpdate));
            companyToUpdate.setResponsiblePerson(newResponsiblePersons);
            Company createdCompany = companyManagementFacade.updateCompany(companyToUpdate);
            for (UserDto userDTO : newResponsiblePersons) {
                userDTO.getCompanies().add(createdCompany);
                companyManagementFacade.addCompanyToUser(userDTO, createdCompany);
            }
            redirectAttributes.addFlashAttribute("success", "The company has been successfully updated.");
            return "redirect:/company/" + session.getAttribute("nip");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while updating the company: " + e.getMessage());
            return "redirect:/company/edit/" + companyToUpdate.getNip();
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
                return "redirect:/company/" + session.getAttribute("nip");
            } else {
                redirectAttributes.addFlashAttribute("error", "Address not found");
                return "redirect:/company/edit-shipping-address/" + addressToUpdate.getId();
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while updating the address: " + e.getMessage());
            return "redirect:/company/edit-shipping-address/" + addressToUpdate.getId();
        }
    }

    @GetMapping("/companies-database")
    public String showCustomersDatabase(@RequestParam(name = "salesEngineerIds", required = false) List<Long> salesEngineerIds,
                                        Authentication authentication, Model model) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        List<Company> allCompanies = new ArrayList<>();
        companyManagementFacade.findAllCompanies().forEach(allCompanies::add);

        Set<Company> filteredCompanies = new HashSet<>();

        if (salesEngineerIds != null && !salesEngineerIds.isEmpty()) {
            for (Long salesEngineerId : salesEngineerIds) {
                User salesEngineer = companyManagementFacade.findUserById(salesEngineerId);
                if (salesEngineer != null) {
                    List<Company> companiesForSalesEngineer = companyManagementFacade.findCompaniesByResponsiblePerson(salesEngineer);
                    filteredCompanies.addAll(companiesForSalesEngineer);
                }
            }
        } else {
            filteredCompanies.addAll(allCompanies);
        }

        model.addAttribute("allCompanies", filteredCompanies);
        model.addAttribute("salesEngineers", companyManagementFacade.getUsersByRole(SALES_ENGINEER_ROLE));

        return "company/companies-database";
    }


    @GetMapping("/delete-company/{nip}")
    public String deleteCompany(@PathVariable Long nip,
                              RedirectAttributes redirectAttributes,
                              @RequestParam String action,
                              Authentication authentication,
                              Model model) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);
        if ("deleteCompany".equals(action)) {
            boolean deleted = companyManagementFacade.deleteCompany(nip);
            if (deleted) {
                redirectAttributes.addFlashAttribute("success", "Firma została usunięta");
            } else {
                redirectAttributes.addFlashAttribute("error", "Firma nie została znaleziona");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Podczas usuwania wystąpił błąd");
        }
        return "redirect:/company/customers-database";
    }
}
