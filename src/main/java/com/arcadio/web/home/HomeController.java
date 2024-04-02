package com.arcadio.web.home;

import com.arcadio.domain.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {
    private final UserUtils userUtils;
    private final HomeService homeService;

    public HomeController(UserUtils userUtils, HomeService homeService) {
        this.userUtils = userUtils;
        this.homeService = homeService;
    }

    @GetMapping("/")
    String home(Authentication authentication, HttpServletRequest request, HttpServletResponse response, Model model) {
        Long userId = userUtils.getUserIdFromAuthentication(authentication);
        if (userId == null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return "redirect:/login";
        }

        userUtils.addAvatarUrlToModel(authentication, model);
        userUtils.getUserEmailToModel(authentication, model);
        userUtils.getUserRoleToModel(authentication, model);

        return "index";
    }

}
