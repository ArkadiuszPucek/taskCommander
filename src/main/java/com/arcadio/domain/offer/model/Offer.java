package com.arcadio.domain.offer.model;

import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.components.model.Components;
import com.arcadio.domain.construction.model.Construction;
import com.arcadio.domain.customer.model.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "offer")
@Getter
@Setter
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "offer_id", unique = true, nullable = false)
    private String offerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "offer_date")
    private LocalDateTime offerDate;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "margin")
    private BigDecimal margin;

    @Column(name = "purchase_cost")
    private BigDecimal purchaseCost;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "offer_components",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private Set<Components> components = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "offer_component_prices", joinColumns = @JoinColumn(name = "offer_id"))
    @MapKeyJoinColumn(name = "component_id")
    @Column(name = "price")
    private Map<Components, BigDecimal> originalComponentPrices = new HashMap<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Construction> constructions = new HashSet<>();
}
