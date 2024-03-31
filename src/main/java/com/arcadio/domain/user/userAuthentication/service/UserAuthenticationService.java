package com.arcadio.domain.user.userAuthentication.service;

import com.arcadio.domain.exceptions.InvalidPasswordException;
import com.arcadio.domain.exceptions.PasswordConfirmationException;
import com.arcadio.domain.exceptions.PasswordFormatException;
import com.arcadio.domain.exceptions.UserNotFoundException;
import com.arcadio.domain.user.userAuthentication.dto.UserCredentialsDto;
import com.arcadio.domain.user.userAuthentication.dto.UserCredentialsDtoMapper;
import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userDetails.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserAuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Optional<UserCredentialsDto> findCredentialsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserCredentialsDtoMapper::map);
    }

    @Transactional
    public void changePassword(String oldPassword, String newPassword, String confirmPassword, Long userId)
            throws InvalidPasswordException, PasswordConfirmationException, PasswordFormatException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        validatePasswordChange(oldPassword, newPassword, confirmPassword, user);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private void validatePasswordChange(String oldPassword, String newPassword, String confirmPassword, User user)
            throws InvalidPasswordException, PasswordConfirmationException, PasswordFormatException {

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new InvalidPasswordException("Current password is incorrect.");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordConfirmationException("The passwords do not match.");
        }

        String passwordRegex = "^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{8,}$";
        if (!newPassword.matches(passwordRegex)) {
            throw new PasswordFormatException("Password must contain at least one uppercase letter, one number, one special character, and be at least 8 characters long.");
        }
    }
}
