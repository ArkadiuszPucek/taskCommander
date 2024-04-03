package com.arcadio.domain.offer.controller;

import com.arcadio.domain.offer.dto.OfferDTO;
import com.arcadio.domain.offer.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

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
