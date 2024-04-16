package com.arcadio.domain.user.userDetails.dto;

import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userRole.model.UserRole;
import com.arcadio.domain.user.userRole.service.UserRoleService;

import java.util.Collections;

public class UserMapper {

    public static UserDto mapToUserDto(User user, UserRole role) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setAvatar(user.getAvatar());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setArea(user.getArea());
        userDto.setRole(role);
        userDto.setCompanies(user.getCompanies());
        return userDto;
    }

    public static User mapToUser(UserDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setArea(userDTO.getArea());
        user.setPassword(userDTO.getPassword());
        user.setAvatar(userDTO.getAvatar());
        user.setEmail(userDTO.getEmail());
        user.setRoles(Collections.singleton(userDTO.getRole()));
        user.setCompanies(userDTO.getCompanies());
        return user;
    }

//    private UserRole getUserRole(User user) {
//        String roleNames = user.getRoleNames();
//
//        UserRole role = new UserRole();
//        role.setName(roleNames);
//        return role;
//    }


}
