package com.arcadio.domain.customer.dto;

import com.arcadio.domain.company.model.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private Company company;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String position;
}

