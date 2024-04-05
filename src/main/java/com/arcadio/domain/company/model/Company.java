package com.arcadio.domain.company.model;

import com.arcadio.domain.adresses.billingAddress.BillingAddress;
import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.customer.model.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long nip;

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShippingAddress> shippingAddresses = new HashSet<>();

    @Column(name = "currency")
    private Currency currency;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BillingAddress> billingAddresses = new HashSet<>();

    @Column(name = "additional_notes")
    private String additionalNotes;

    @OneToMany(mappedBy = "company")
    private Set<Customer> customers = new HashSet<>();
}

