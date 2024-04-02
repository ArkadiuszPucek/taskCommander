package com.arcadio.domain;

import com.arcadio.domain.user.UserFacade;
import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userDetails.repository.UserRepository;
import com.arcadio.domain.user.userRole.model.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Optional;
import java.util.Set;

@Component
public class UserUtils {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    public UserUtils(UserFacade userFacade, UserRepository userRepository) {
        this.userFacade = userFacade;
        this.userRepository = userRepository;
    }
    public void addAvatarUrlToModel(Authentication authentication, Model model) {
        if (authentication != null) {
            String currentUsername = authentication.getName();
            String avatarUrl = userFacade.getAvatarUrlByUsername(currentUsername);
            model.addAttribute("avatar", avatarUrl);
        }
    }

    public void getUserEmailToModel(Authentication authentication, Model model) {
        if (authentication != null) {
            String userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }
    }

    public void getUserRoleToModel(Authentication authentication, Model model) {
        if (authentication != null) {
            String userRole = userFacade.getUserRole(authentication.getName());
            model.addAttribute("userRole", userRole);
        }
    }

    public Long getUserIdFromAuthentication(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();
            Optional<User> userOpt = userRepository.findByEmail(email);
            return userOpt.map(User::getId).orElse(null);
        }
        return null;
    }

    public void deleteUserAndHandleLogout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, Long userId) {
        userFacade.deleteUserAndHandleLogout(request, response, redirectAttributes, userId);
    }
}
