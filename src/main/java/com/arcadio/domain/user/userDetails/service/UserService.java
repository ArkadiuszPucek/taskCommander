package com.arcadio.domain.user.userDetails.service;

import com.arcadio.domain.exceptions.UserNotFoundException;
import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userDetails.repository.UserRepository;
import com.arcadio.domain.user.userRole.model.UserRole;
import com.arcadio.domain.user.userRole.service.UserRoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private final UserListService userListService;
    private final UserRoleService userRoleService;

    public UserService(UserRepository userRepository, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }
//    private final ViewingHistoryFacade viewingHistoryFacade;

//    public UserService(UserRepository userRepository, UserRoleService userRoleService, ViewingHistoryFacade viewingHistoryFacade) {
//        this.userRepository = userRepository;
////        this.userListService = userListService;
//        this.userRoleService = userRoleService;
//        this.viewingHistoryFacade = viewingHistoryFacade;
//    }


    public void changeUserRole(Long userId, String newRole) {
        User user = findUserById(userId);
        UserRole role = userRoleService.findRoleByName(newRole);
        user.getRoles().clear();
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public String getUserRole(String username){
        return userRepository.findByEmail(username)
                .map(User::getRoleNames)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
    }

        public String getAvatarUrlByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(User::getAvatar)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
    }

    public List<User> getAllUsersInService() {
        return userRepository.findAll();
    }

    public User findByUsername(String currentUserName) {
        return userRepository.findByEmail(currentUserName).orElseThrow(()-> new UserNotFoundException("User not found"));
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    public boolean deleteUserById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
//                    userListService.removeUserAndAllItems(userId);
//                    viewingHistoryFacade.removeUserAndAllViewingHistory(userId);
                    userRepository.delete(user);
                    return true;
                }).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void deleteUserAndHandleLogout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, Long userId) {
        boolean deleted = deleteUserById(userId);
        if (deleted) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            redirectAttributes.addFlashAttribute("message", "User deleted.");
        } else {
            redirectAttributes.addFlashAttribute("message", "User not found.");
        }
    }

    public void changeEmail(String oldEmail, String newEmail) {
        User user = findByUsername(oldEmail);
        user.setEmail(newEmail);
        userRepository.save(user);
    }
}