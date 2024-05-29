package com.arcadio.domain.offer.dto;

import com.arcadio.domain.components.model.Components;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentQuantityDTO {
    private Components component;
    private int quantity;

    private Double discount;

    public ComponentQuantityDTO(Components component, int quantity, Double discount) {
        this.component = component;
        this.quantity = quantity;
        this.discount = discount;
    }
}
