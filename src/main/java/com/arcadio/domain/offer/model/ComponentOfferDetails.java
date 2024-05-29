package com.arcadio.domain.offer.model;

import com.arcadio.domain.components.model.Components;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "component_offer_details")
@Getter
@Setter
public class ComponentOfferDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_offer_id")
    private ComponentOffer componentOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id")
    private Components component;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "selling_price_after_discount")
    private BigDecimal sellingPriceAfterDiscount;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;
}
