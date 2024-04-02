package com.arcadio.domain.company.model;

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
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "nip")
    private String nip;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "billing_email")
    private String billingEmail;

    @Column(name = "additional_notes")
    private String additionalNotes;

    @OneToMany(mappedBy = "company")
    private Set<Customer> customers = new HashSet<>();
}

