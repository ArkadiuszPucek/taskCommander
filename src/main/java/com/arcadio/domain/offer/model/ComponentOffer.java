package com.arcadio.domain.offer.model;

import com.arcadio.domain.components.model.Components;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "component_offer")
@Getter
@Setter
public class ComponentOffer extends Offer {

    private static final String OFFER_TYPE = "Component";

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "component_offer_components",
            joinColumns = @JoinColumn(name = "component_offer_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private Set<Components> components = new HashSet<>();

    @Column(name = "offer_type")
    String offerType = OFFER_TYPE;

}
