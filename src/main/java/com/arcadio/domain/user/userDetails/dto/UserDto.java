package com.arcadio.domain.user.userDetails.dto;

import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.user.userRole.model.UserRole;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\\d).{8,}$", message = "Provided password is incorrect")
    private String password;
    private String area;
    private String avatar;
    private UserRole role;
    private Set<Company> companies;
}
