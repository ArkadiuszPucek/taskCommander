package com.arcadio.web.admin.usersManagement;

import com.arcadio.domain.UserUtils;
import com.arcadio.domain.user.UserFacade;
import com.arcadio.domain.user.userDetails.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class UserManagementFacade {
    private final UserUtils userUtils;
    private final UserFacade userFacade;


    public UserManagementFacade(UserUtils userUtils, UserFacade userFacade) {
        this.userUtils = userUtils;
        this.userFacade = userFacade;
    }

    public void addAvatarUrlToModel(Authentication authentication, Model model) {
        userUtils.addAvatarUrlToModel(authentication, model);
    }

    public Long getUserIdFromAuthentication(Authentication authentication) {
        return userUtils.getUserIdFromAuthentication(authentication);
    }

    public List<User> getAllUsersInService() {
        return userFacade.getAllUsersInService();
    }

    public void changeUserRole(Long userId, String newRole) {
        userFacade.changeUserRole(userId, newRole);
    }

    public void deleteUser(Long userId) {
        userFacade.deleteUser(userId);
    }

}
