package com.arcadio.domain.components.controller;

import com.arcadio.domain.UserUtils;
import com.arcadio.domain.components.model.Components;
import com.arcadio.domain.components.service.ComponentsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

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

        List<String> categoriesOrder = Arrays.asList("Złączki", "Konektory", "Rury", "Rolki", "Prowadnice", "Koła", "Akcesoria", "Śruby");

        Map<String, List<Components>> componentsByCategory = new LinkedHashMap<>();
        for (String category : categoriesOrder) {
            List<Components> components = componentsService.getComponentsByCategory(category);
            componentsByCategory.put(category, components);
        }

        model.addAttribute("categories", categoriesOrder);
        model.addAttribute("componentsByCategory", componentsByCategory);

        return "components";
    }
}
