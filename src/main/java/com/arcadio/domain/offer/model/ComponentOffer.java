package com.arcadio.domain.offer.model;

import com.arcadio.domain.components.model.Components;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "component_offer")
@Getter
@Setter
public class ComponentOffer extends Offer {

    private static final String OFFER_TYPE = "Component";

    @OneToMany(mappedBy = "componentOffer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ComponentOfferDetails> componentOfferDetails = new HashSet<>();

    @Column(name = "offer_type")
    String offerType = OFFER_TYPE;

    public void addComponentOfferDetail(ComponentOfferDetails detail) {
        componentOfferDetails.add(detail);
        detail.setComponentOffer(this);
    }

    public void removeComponentOfferDetail(ComponentOfferDetails detail) {
        componentOfferDetails.remove(detail);
        detail.setComponentOffer(null);
    }
}

