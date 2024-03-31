package com.arcadio.domain.user.userRole.service;

import com.arcadio.domain.exceptions.UserRoleNotFoundException;
import com.arcadio.domain.user.userRole.model.UserRole;
import com.arcadio.domain.user.userRole.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
    public UserRole findRoleByName(String roleName) {
        return userRoleRepository.findByName(roleName)
                .orElseThrow(() -> new UserRoleNotFoundException("Role not found: " + roleName));
    }
}
