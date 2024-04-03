package com.arcadio.domain.construction.model;


import com.arcadio.domain.components.model.Components;
import com.arcadio.domain.customComponents.model.CustomComponent;
import com.arcadio.domain.offer.model.Offer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "construction")
@Getter
@Setter
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "construction_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @Column(name = "construction_name")
    private String constructionName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "margin")
    private BigDecimal margin;

    @Column(name = "purchase_cost")
    private BigDecimal purchaseCost;

    @Column(name = "installation_hours")
    private int installationHours;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "construction_components",
            joinColumns = @JoinColumn(name = "construction_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private Set<Components> components = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "construction_custom_components", joinColumns = @JoinColumn(name = "construction_id"))
    private Set<CustomComponent> customComponents = new HashSet<>();
}
