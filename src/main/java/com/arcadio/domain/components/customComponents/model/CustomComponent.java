package com.arcadio.domain.components.customComponents.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Embeddable
@Getter
@Setter
public class CustomComponent {

    private String name;
    private int quantity;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;

}
