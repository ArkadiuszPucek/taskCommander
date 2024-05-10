package com.arcadio.domain.offer.model;

import com.arcadio.domain.construction.model.Construction;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "construction_offer")
@Getter
@Setter
public class ConstructionOffer extends Offer{
    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Construction> constructions = new HashSet<>();
}
