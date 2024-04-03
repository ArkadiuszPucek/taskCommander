package com.arcadio.domain.components.controller;

import com.arcadio.domain.UserUtils;
import com.arcadio.domain.components.service.ComponentsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/components")
public class ComponentsController {

    private UserUtils userUtils;
    private ComponentsService componentsService;

    public ComponentsController(UserUtils userUtils, ComponentsService componentsService) {
        this.userUtils = userUtils;
        this.componentsService = componentsService;
    }


    @GetMapping
    public String componentsPage(Authentication authentication, Model model) {
        userUtils.addAvatarUrlToModel(authentication, model);
        userUtils.getUserEmailToModel(authentication, model);
        userUtils.getUserRoleToModel(authentication, model);
        Long userId = userUtils.getUserIdFromAuthentication(authentication);

        model.addAttribute("connectors", componentsService.getComponentsByCategory("Konektory"));
        return "components";
    }
}
