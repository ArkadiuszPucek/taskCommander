package com.arcadio.domain.user.userDetails.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\\d).{8,}$", message = "Provided password is incorrect")
    private String password;
}
