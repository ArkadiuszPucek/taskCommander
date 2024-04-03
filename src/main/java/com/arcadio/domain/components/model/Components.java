package com.arcadio.domain.components.model;

import com.arcadio.domain.offer.model.Offer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "image_path") // Dodane pole dla ścieżki do zdjęcia
    private String imagePath;

    @Column(name = "category") // Pole reprezentujące kategorię komponentu (np. konektory, rury, rolki, etc.)
    private String category;

    @Column(name = "variant") // Pole reprezentujące wariant komponentu (np. graphit, leanTek, 28,0 mm)
    private String variant;

    @ManyToMany(mappedBy = "components")
    private Set<Offer> offers = new HashSet<>();
}
