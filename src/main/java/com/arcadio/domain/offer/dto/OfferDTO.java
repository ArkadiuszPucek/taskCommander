package com.arcadio.domain.offer.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Getter
@Setter
public class OfferDTO {
    private Long id;
    private String offerId;
    private Long companyId;
    private Long customerId;
    private LocalDateTime offerDate;
    private BigDecimal totalPrice;
    private BigDecimal margin;
    private BigDecimal purchaseCost;
    private Set<Long> componentIds = new HashSet<>();
    private Map<Long, BigDecimal> originalComponentPrices;
    private Set<Long> constructionIds = new HashSet<>();

}
