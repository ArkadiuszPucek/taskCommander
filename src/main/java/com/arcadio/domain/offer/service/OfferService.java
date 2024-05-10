package com.arcadio.domain.offer.service;

import com.arcadio.domain.components.model.Components;
import com.arcadio.domain.components.repository.ComponentsRepository;
import com.arcadio.domain.offer.dto.OfferDTO;
import com.arcadio.domain.offer.model.Offer;
import com.arcadio.domain.offer.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class OfferService {

    @Autowired
    private  OfferRepository offerRepository;

    @Autowired
    private ComponentsRepository componentsRepository;

//    public OfferDTO createOffer(OfferDTO offerDTO) {
//
////        // Pobranie komponentów na podstawie ich identyfikatorów
////        List<Components> components = new ArrayList<>();
////        for (String componentId : offerDTO.getComponentIds()) {
////            Components component = componentsRepository.findById(componentId)
////                    .orElseThrow(() -> new ComponentNotFoundException("Component not found"));
////            components.add(component);
////        }
//
//        // Utworzenie oferty
//        Offer offer = new Offer();
////        offer.setCompany(offerDTO.getCompany());
////        offer.setCustomer(offerDTO.getCustomer());
//        offer.setOfferDate(LocalDateTime.now());
//        offer.setTotalPrice(offerDTO.getTotalPrice());
//        offer.setMargin(offerDTO.getMargin());
//        offer.setPurchaseCost(offerDTO.getPurchaseCost());
////        offer.setComponents(components);
//
//        // Ustawienie oryginalnych cen komponentów
//        setOriginalComponentPrices(offer);
//
//        // Zapisanie oferty
//        Offer savedOffer = offerRepository.save(offer);
//
//        // Konwersja i zwrócenie DTO zapisanej oferty
//        return convertToDTO(savedOffer);
//    }
//
////    private OfferDTO convertToDTO(Offer offer) {
////        // Implementacja konwersji oferty na DTO
////    }


//    public void setOriginalComponentPrices(Offer offer) {
//        Map<Components, BigDecimal> originalComponentPrices = new HashMap<>();
//        for (Components component : offer.getComponents()) {
//            originalComponentPrices.put(component, component.getPurchasePrice());
//        }
//        offer.setOriginalComponentPrices(originalComponentPrices);
//    }


//    public OfferDTO getOfferById(Long offerId) {
//        // Implementacja logiki biznesowej
//    }



}
