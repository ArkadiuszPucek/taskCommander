package com.arcadio.domain.offer.controller;

import com.arcadio.domain.company.CompanyManagementFacade;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.customer.CustomerManagementFacade;
import com.arcadio.domain.customer.model.Customer;
import com.arcadio.domain.offer.model.ComponentOffer;
import com.arcadio.domain.offer.model.ConstructionOffer;
import com.arcadio.domain.offer.model.Offer;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;


@Controller
@RequestMapping("/inquiry")
public class OfferController {

    private final CompanyManagementFacade companyManagementFacade;
    private final CustomerManagementFacade customerManagementFacade;

    public OfferController(CompanyManagementFacade companyManagementFacade, CustomerManagementFacade customerManagementFacade) {
        this.companyManagementFacade = companyManagementFacade;
        this.customerManagementFacade = customerManagementFacade;
    }

    @GetMapping
    public String showInquiryForm(Model model, Authentication authentication) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        Iterable<Company> allCompanies = companyManagementFacade.findAllCompanies();
        model.addAttribute("companies", companyManagementFacade.findAllCompanies());
        return "inquiry/inquiry-form";
    }

    @PostMapping("/create-offer")
    public String createOffer(@RequestParam("offerType") String offerType,
                              @RequestParam("companyId") Long nip,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        
        String da = offerType;
        Offer offer;
        if ("Component".equals(offerType)) {
            offer = new ComponentOffer();
        } else if ("Construction".equals(offerType)) {
            offer = new ConstructionOffer();
        } else {
            return "error_page";
        }

        session.setAttribute("nip", nip);

        Company companyByNip = companyManagementFacade.getCompanyByNip(nip);

        offer.setCompany(companyByNip);

        return "redirect:/create-offer";
    }
    @GetMapping("/create-offer")
    public String addCustomerToOffer(Model model, Authentication authentication, RedirectAttributes redirectAttributes , HttpSession session) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        Long nip = (Long) session.getAttribute("nip");

        Company companyByNip = companyManagementFacade.getCompanyByNip(nip);
        Set<Customer> customers = companyByNip.getCustomers();
        model.addAttribute("customers", customers);

        return "/inuiry/add-cutomer-to-offer-form";
    }

//    @Autowired
//    private OfferService offerService;

//    @GetMapping("/{offerId}")
//    public ResponseEntity<OfferDTO> getOffer(@PathVariable Long offerId) {
//        OfferDTO offerDTO = offerService.getOfferById(offerId);
//        return ResponseEntity.ok(offerDTO);
//    }

//    @PostMapping
//    public ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO) {
//        OfferDTO createdOffer = offerService.createOffer(offerDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdOffer);
}
