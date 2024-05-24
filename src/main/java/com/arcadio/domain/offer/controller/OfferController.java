package com.arcadio.domain.offer.controller;

import com.arcadio.domain.company.CompanyManagementFacade;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.components.model.Components;
import com.arcadio.domain.components.service.ComponentsService;
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

import java.util.*;


@Controller
@RequestMapping("/inquiry")
public class OfferController {

    private final CompanyManagementFacade companyManagementFacade;
    private final CustomerManagementFacade customerManagementFacade;
    private final ComponentsService componentsService;

    public OfferController(CompanyManagementFacade companyManagementFacade, CustomerManagementFacade customerManagementFacade, ComponentsService componentsService) {
        this.companyManagementFacade = companyManagementFacade;
        this.customerManagementFacade = customerManagementFacade;
        this.componentsService = componentsService;
    }

    @GetMapping
    public String showInquiryForm(Model model, Authentication authentication) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        model.addAttribute("companies", companyManagementFacade.findAllCompanies());
        return "inquiry/inquiry-form";
    }

    @PostMapping("/create-offer")
    public String createOffer(@RequestParam("offerType") String offerType,
                              @RequestParam("nip") Long nip,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        
        Offer offer;
        if ("Component".equals(offerType)) {
            offer = new ComponentOffer();
        } else if ("Construction".equals(offerType)) {
            offer = new ConstructionOffer();
        } else {
            offer = null;
        }

        session.setAttribute("nip", nip);

        Company company = companyManagementFacade.getCompanyByNip(nip);
        offer.setCompany(company);

        session.setAttribute("offer", offer);

        return "redirect:/inquiry/create-offer/add-customer";
    }

    @GetMapping("/create-offer/add-customer")
    public String addCustomerToOffer(Model model, Authentication authentication, HttpSession session) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);

        Long nip = (Long) session.getAttribute("nip");
        Company company = companyManagementFacade.getCompanyByNip(nip);
        Set<Customer> customers = company.getCustomers();
        model.addAttribute("customers", customers);

        return "inquiry/add-customer-to-offer-form";
    }

    @PostMapping("/create-offer/add-customer")
    public String addCustomerToOffer(@RequestParam("customerId") Long customerId,
                                     HttpSession session) {
        Offer offer = (Offer) session.getAttribute("offer");
        Customer customer = customerManagementFacade.getCustomerById(customerId);
        offer.setCustomer(customer);

        return "redirect:/inquiry/create-offer/add-components";
    }

    @GetMapping("/create-offer/add-components")
    public String addComponentsToOffer(Model model, Authentication authentication, HttpSession session) {
        companyManagementFacade.addAvatarUrlToModel(authentication, model);
        companyManagementFacade.addUserRoleToModel(authentication, model);
        companyManagementFacade.addUserEmailToModel(authentication, model);
        ComponentOffer offer = (ComponentOffer) session.getAttribute("offer");

        List<String> categoriesOrder = Arrays.asList("Złączki", "Konektory", "Rury", "Rolki", "Prowadnice", "Koła", "Akcesoria", "Śruby");

        Map<String, List<Components>> componentsToUI = new LinkedHashMap<>();
        for (String category : categoriesOrder) {
            List<Components> components = componentsService.getComponentsByCategory(category);
            componentsToUI.put(category, components);
        }

        model.addAttribute("offer", offer);
        model.addAttribute("categories", categoriesOrder);
        model.addAttribute("components", componentsToUI);

        return "inquiry/add-components-form";
    }

    @PostMapping("/create-offer/add-components")
    public String addComponentsToOffer(@RequestParam Map<String, String> requestParams, HttpSession session) {
        Offer offer = (Offer) session.getAttribute("offer");
        if (!(offer instanceof ComponentOffer)) {
            return "redirect:/not-found";
        }
        ComponentOffer componentOffer = (ComponentOffer) offer;

        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            if (entry.getKey().startsWith("quantity_")) {
                String componentId = entry.getKey().substring(9);
                Integer quantity = Integer.parseInt(entry.getValue());

                if (quantity > 0) {
                    Components component = componentsService.getComponentById(componentId);


                }
            }
        }

        session.setAttribute("offer", componentOffer);

        return "redirect:/inquiry/create-offer/summary";
    }

    @GetMapping("/create-offer/summary")
    public String showSummary(Model model, HttpSession session) {
        // Pobierz ofertę z sesji
        Offer offer = (Offer) session.getAttribute("offer");

        // Dodaj ofertę do modelu
        model.addAttribute("offer", offer);

        // Zwróć widok podsumowania
        return "inquiry/offer-summary";
    }

    @PostMapping("/create-offer/summary")
    public String proceedSummary(@ModelAttribute("offer") Offer offer,
                                 @RequestParam("orderCompletionDate") Integer orderCompletionDate,
                                 @RequestParam("validityTerm") Integer validityTerm,
                                 @RequestParam("deliveryMethod") String deliveryMethod,
                                 HttpSession session) {

        offer.setOrderCompletionDate(orderCompletionDate);
        offer.setValidityTerm(validityTerm);
        offer.setDeliveryMethod(deliveryMethod);
        // Pobierz ofertę z sesji
//        Offer offer = (Offer) session.getAttribute("offer");

        // Dodaj ofertę do modelu
//        model.addAttribute("offer", offer);

        // Zwróć widok podsumowania
        return "inquiry/offer-summary";
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
