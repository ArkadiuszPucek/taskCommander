package com.arcadio.domain.company.model;

import com.arcadio.domain.adresses.shippingaddress.ShippingAddress;
import com.arcadio.domain.customer.model.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @Column(name = "company_id")
    private Long nip;

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShippingAddress> shippingAddresses = new HashSet<>();

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "billing_mail")
    @Email(message = "Invalid email address")
    private String billingMail;

    @Column(name = "billing_street_name")
    private String billingStreetName;

    @Column(name = "billing_building_number")
    private String billingBuildingNumber;

    @Column(name = "billing_apartment_number")
    private String billingApartmentNumber;

    @Column(name = "billing_city")
    private String billingCity;

    @Column(name = "billing_postal_code")
    private String billingPostalCode;

    @Column(name = "additional_notes")
    private String additionalNotes;

    @OneToMany(mappedBy = "company")
    private Set<Customer> customers = new HashSet<>();
}

