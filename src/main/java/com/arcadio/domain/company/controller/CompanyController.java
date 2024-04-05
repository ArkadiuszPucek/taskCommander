package com.arcadio.domain.company.controller;

import com.arcadio.domain.company.CompanyManagementFacade;
import com.arcadio.domain.company.dto.CompanyDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clients")
public class CompanyController {
    private final CompanyManagementFacade companyManagementFacade;

    public CompanyController(CompanyManagementFacade companyManagementFacade) {
        this.companyManagementFacade = companyManagementFacade;
    }


    @PostMapping("/add-company")
    public String addMovieManual(CompanyDTO company, RedirectAttributes redirectAttributes) {
        if (companyManagementFacade.doesCompanyExists(company.getNip())) {
            redirectAttributes.addFlashAttribute("error", "Company with the given NIP exists on the website!");
            return "redirect:/add-company";
        }
        companyManagementFacade.addCompany(company);
        Long nip = company.getNip();
//        String normalizedTitle = movieManagementFacade.getNormalizedMovieTitle(movie.getTitle());


        return "redirect:/movies/" + nip;
    }

    @GetMapping("/add-company")
    public String showAddCompanyForm(Authentication authentication , Model model) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        CompanyDTO company = new CompanyDTO();
        model.addAttribute("company", company);

        return "/add-company-form";
    }

}
