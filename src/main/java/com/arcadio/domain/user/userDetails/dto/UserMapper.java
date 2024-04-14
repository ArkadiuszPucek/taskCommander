package com.arcadio.domain.user.userDetails.dto;

import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userRole.model.UserRole;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setArea(user.getArea());
        userDto.setRole(getUserRole(user));
        return userDto;
    }

    private static UserRole getUserRole(User user) {
        String roleNames = user.getRoleNames();
        UserRole role = new UserRole();
        role.setName(roleNames);
        return role;
    }


}
