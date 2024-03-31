package com.arcadio.domain.user.userDetails.model;

import com.arcadio.domain.user.userRatings.model.UserRating;
import com.arcadio.domain.user.userRole.model.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Length(min = 8, message = "Hasło musi mieć co najmniej 8 znaków.")
    private String password;
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<UserRole> roles = new HashSet<>();

    public String getRoleNames() {
        return roles.stream()
                .map(UserRole::getName)
                .collect(Collectors.joining(", "));
    }
}
