package com.arcadio.domain.offer.model;

import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.components.model.Components;
import com.arcadio.domain.construction.model.Construction;
import com.arcadio.domain.customer.model.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "offer")
@Getter
@Setter
public abstract class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offer_id_seq")
    @SequenceGenerator(name = "offer_id_seq", sequenceName = "offer_id_seq", allocationSize = 1)
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
    private LocalDate offerDate;

    @Column(name = "order_completion_date")
    private Integer orderCompletionDate;

    @Column(name = "validity_term")
    private Integer validityTerm;

    @Column(name = "delivery_method")
    private String deliveryMethod;

//    @ElementCollection
//    @CollectionTable(name = "offer_component_prices", joinColumns = @JoinColumn(name = "offer_id"))
//    @MapKeyJoinColumn(name = "component_id")
//    @Column(name = "price")
//    private Map<Components, BigDecimal> originalComponentPrices = new HashMap<>();

}
