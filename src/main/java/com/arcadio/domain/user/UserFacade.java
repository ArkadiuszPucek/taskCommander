package com.arcadio.domain.user;

import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.exceptions.InvalidPasswordException;
import com.arcadio.domain.exceptions.PasswordConfirmationException;
import com.arcadio.domain.exceptions.PasswordFormatException;
import com.arcadio.domain.exceptions.UsernameExistsException;
import com.arcadio.domain.user.userAuthentication.service.UserAuthenticationService;
import com.arcadio.domain.user.userAuthentication.service.UserRegistrationService;
import com.arcadio.domain.user.userAvatar.service.UserAvatarService;
import com.arcadio.domain.user.userDetails.dto.UserDto;
import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userDetails.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Service
public class UserFacade {
    private final UserService userService;
    private final UserRegistrationService userRegistrationService;
    private final UserAvatarService userAvatarService;
    private final UserAuthenticationService userAuthenticationService;

    public UserFacade(UserService userService, UserRegistrationService userRegistrationService, UserAvatarService userAvatarService, UserAuthenticationService userAuthenticationService) {
        this.userService = userService;
        this.userRegistrationService = userRegistrationService;
        this.userAvatarService = userAvatarService;
        this.userAuthenticationService = userAuthenticationService;
    }
    public void changeUserRole(Long userId, String newRole) {
        userService.changeUserRole(userId, newRole);
    }

    public String getUserRole(String username){
        return userService.getUserRole(username);

    }

    public String getAvatarUrlByUsername(String username) {
        return userService.getAvatarUrlByUsername(username);
    }

    public List<User> getAllUsersInService() {
        return userService.getAllUsersInService();
    }

    public User findByUsername(String currentUserName) {
        return userService.findByUsername(currentUserName);
    }
    public void changeEmail(String oldEmail, String newEmail) {
        userService.changeEmail(oldEmail, newEmail);
    }

    public void registerNewUserAccount(UserDto userDto) throws UsernameExistsException {
        userRegistrationService.registerNewUserAccount(userDto);
    }
    public void changeAvatar(Long userId, String newAvatar) {
        userAvatarService.changeAvatar(userId, newAvatar);
    }

    public List<String> getAvailableAvatarPaths() {
        return userAvatarService.getAvailableAvatarPaths();
    }

    public void changePassword(String oldPassword, String newPassword, String confirmPassword, Long userId) throws PasswordFormatException, InvalidPasswordException, PasswordConfirmationException {
        userAuthenticationService.changePassword(oldPassword, newPassword, confirmPassword, userId);
    }

    public void deleteUserAndHandleLogout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, Long userId) {
        userService.deleteUserAndHandleLogout(request, response, redirectAttributes, userId);
    }

    public void deleteUser(Long userId){
        userService.deleteUserById(userId);
    }

    public List<User> getUsersByRole(String userRole) {
        return userService.getUsersByRole(userRole);
    }

    public Set<UserDto> findUsersByIds(List<Long> responsiblePersonIds) {
        return userService.findUsersByIds(responsiblePersonIds);
    }

    public void addCompanyToUser(UserDto user, Company company) {
        userService.addCompanyToUser(user, company);
    }

    public User findUserById(Long id) {
        return userService.findUserById(id);
    }
}
