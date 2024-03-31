package com.arcadio.domain.user.userAuthentication.service;

import com.arcadio.domain.exceptions.UsernameExistsException;
import com.arcadio.domain.user.userDetails.dto.UserDto;
import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userDetails.repository.UserRepository;
import com.arcadio.domain.user.userRole.model.UserRole;
import com.arcadio.domain.user.userRole.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private static final String DEFAULT_AVATAR_WOLF = "/images/avatars/wilk2.png";
    private static final String ROLE_USER = "USER";

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional
    public User registerNewUserAccount(UserDto userDto) throws UsernameExistsException {
        validateUsername(userDto.getUsername());

        User newUser = new User();
        newUser.setEmail(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setAvatar(DEFAULT_AVATAR_WOLF);

        UserRole role = userRoleRepository.findByName(ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Default user role not found"));
        newUser.getRoles().add(role);

        return userRepository.save(newUser);
    }

    private void validateUsername(String username) throws UsernameExistsException {
        if (userRepository.findByEmail(username).isPresent()) {
            throw new UsernameExistsException("An account with the provided email address already exists.");
        }
    }
}
