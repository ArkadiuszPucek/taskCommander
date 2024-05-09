package com.arcadio.domain.user.userDetails.service;

import com.arcadio.domain.company.dto.CompanyDTO;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.company.service.CompanyService;
import com.arcadio.domain.exceptions.UserNotFoundException;
import com.arcadio.domain.user.userDetails.dto.UserDto;
import com.arcadio.domain.user.userDetails.dto.UserMapper;
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

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private final UserListService userListService;
    private final UserRoleService userRoleService;
    private final CompanyService companyService;

    public UserService(UserRepository userRepository, UserRoleService userRoleService, CompanyService companyService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.companyService = companyService;
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

    public List<User> getUsersByRole(String role) {
        UserRole sales_engineer = userRoleService.findRoleByName(role);
        return userRepository.findAllByRoles(sales_engineer);
    }

    public Set<UserDto> findUsersByIds(List<Long> responsiblePersonIds) {
        Set<UserDto> users = new HashSet<>();
        for (Long id : responsiblePersonIds) {
            Optional<User> userOptional = userRepository.findById(id);
            userOptional.ifPresent(user -> {
                UserRole role = userRoleService.getUserRole(user);
                UserDto userDto = UserMapper.mapToUserDto(user, role);
                users.add(userDto);
            });
        }
        return users;
    }

    public void addCompanyToUser(UserDto userDTO, Company company) {
        Optional<User> optionalUser = userRepository.findById(userDTO.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setCompanies(userDTO.getCompanies());
            user.getCompanies().add(company);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found with ID: " + userDTO.getId());
        }
    }

    public void removeCompanyFromUser(User oldUser, CompanyDTO companyToUpdate) {
        User user = userRepository.findUserById(oldUser.getId());
        if (user != null){
            Company companyToRemove = companyService.getCompanyByNip(companyToUpdate.getNip());
            user.setCompanies(user.getCompanies());
            user.getCompanies().remove(companyToRemove);
            userRepository.save(user);
        }else {
            throw new UserNotFoundException("User not found with ID: " + user.getId());
        }
    }
}