package com.arcadio.domain.user.userAuthentication.controller;

import com.arcadio.domain.exceptions.UsernameExistsException;
import com.arcadio.domain.user.UserFacade;
import com.arcadio.domain.user.userDetails.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private final UserFacade userFacade;

    public RegisterController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }


    @GetMapping("/register")
    private String showRegistrationForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);

        Authentication login = SecurityContextHolder.getContext().getAuthentication();

        if (login != null && login.isAuthenticated() && !(login instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }
        return "register-form";
    }

    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("user") UserDto userDto,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register-form";
        }
        try {
            userFacade.registerNewUserAccount(userDto);
            redirectAttributes.addFlashAttribute("message", "Registration successful.");
            return "redirect:/login";
        } catch (UsernameExistsException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/register";
        }
    }


}
