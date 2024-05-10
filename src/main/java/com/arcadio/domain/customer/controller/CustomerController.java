package com.arcadio.domain.customer.controller;

import com.arcadio.domain.adresses.shippingaddress.dto.ShippingAddressDTO;
import com.arcadio.domain.company.CompanyManagementFacade;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.customer.CustomerManagementFacade;
import com.arcadio.domain.customer.dto.CustomerDTO;
import com.arcadio.domain.customer.model.Customer;
import com.arcadio.domain.user.userDetails.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CompanyManagementFacade companyManagementFacade;
    private final CustomerManagementFacade customerManagementFacade;

    public CustomerController(CompanyManagementFacade companyManagementFacade, CustomerManagementFacade customerManagementFacade) {
        this.companyManagementFacade = companyManagementFacade;
        this.customerManagementFacade = customerManagementFacade;
    }


    @GetMapping("/add-client")
    public String showAddClientForm(Authentication authentication, Model model) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        CustomerDTO customer = new CustomerDTO();
        Iterable<Company> allCompanies = companyManagementFacade.findAllCompanies();
        model.addAttribute("customer", customer);
        model.addAttribute("companies", allCompanies);

        return "customers/add-client-form";
    }

    @PostMapping("/add-client")
    public String addClient(CustomerDTO customer, RedirectAttributes redirectAttributes, @RequestParam("customerCompany") Long customerCompanyNip) {
        if (customerManagementFacade.doesCustomerExists(customer)) {
            redirectAttributes.addFlashAttribute("error", "Customer exists on the website!");
            return "redirect:/customers/add-client";
        }

        Company companyByNip = companyManagementFacade.getCompanyByNip(customerCompanyNip);
        customerManagementFacade.addCustomer(customer, companyByNip);

        return "redirect:/customers/" + customerCompanyNip;
    }

    @GetMapping("/{nip}")
    public String showCustomersDataBaseForCompanyByNip(Authentication authentication, Model model, @PathVariable Long nip) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        Company company = companyManagementFacade.getCompanyByNip(nip);
        Set<Customer> customers = company.getCustomers();
        model.addAttribute("company", company);
        model.addAttribute("customers", customers);

        return "customers/customers-database";
    }

    @GetMapping("/delete-customer/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId,
                                 RedirectAttributes redirectAttributes,
                                 @RequestParam String action,
                                 HttpSession session,
                                 Authentication authentication,
                                 Model model) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);
        if ("deleteCustomer".equals(action)) {
            boolean deleted = customerManagementFacade.deleteCustomer(customerId);
            if (deleted) {
                redirectAttributes.addFlashAttribute("success", "Klient został usunięty");
            } else {
                redirectAttributes.addFlashAttribute("error", "Klient nie został znaleziony");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Podczas usuwania wystąpił błąd");
        }
        return "redirect:/customers/" + session.getAttribute("nip");
    }

    @GetMapping("/edit-customer/{customerId}")
    public String updateCustomer(@PathVariable Long customerId, Authentication authentication, Model model, HttpSession session) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        Customer customerToEdit = customerManagementFacade.getCustomerById(customerId);
        Iterable<Company> allCompanies = companyManagementFacade.findAllCompanies();
        model.addAttribute("customerToUpdate", customerToEdit);
        model.addAttribute("companies", allCompanies);

        return "customers/edit-customer-form";
    }

    @PostMapping("/edit-customer")
    public String updateCustomer(@ModelAttribute("customerToUpdate") CustomerDTO customerToUpdate, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            boolean updateResult = customerManagementFacade.updateCustomer(customerToUpdate);
            if (updateResult) {
                redirectAttributes.addFlashAttribute("success", "The address has been successfully updated.");
                return "redirect:/customers/" + session.getAttribute("nip");
            } else {
                redirectAttributes.addFlashAttribute("error", "Address not found");
                return "redirect:/customers/edit-customer/" + customerToUpdate.getId();
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while updating the address: " + e.getMessage());
            return "redirect:/customers/edit-customer/" + customerToUpdate.getId();
        }
    }
}
