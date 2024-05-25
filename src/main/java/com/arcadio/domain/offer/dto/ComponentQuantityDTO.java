package com.arcadio.domain.offer.dto;

import com.arcadio.domain.components.model.Components;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentQuantityDTO {
    private Components component;
    private int quantity;

    public ComponentQuantityDTO(Components component, int quantity) {
        this.component = component;
        this.quantity = quantity;
    }
}
