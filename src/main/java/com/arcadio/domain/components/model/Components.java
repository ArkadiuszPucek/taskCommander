package com.arcadio.domain.components.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "components")
@Getter
@Setter
public class Components {

    @Id
    @Column(name = "component_id")
    private String componentId;

    @Column(name = "description")
    private String description;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @Column(name = "selling_price")
    private BigDecimal sellingPrice;
}
