package com.sportcenterplatform.controller;

import com.sportcenterplatform.config.UserAuthenticationProvider;
import com.sportcenterplatform.dto.*;
import com.sportcenterplatform.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling authentication-related endpoints.
 */
@Controller
@RequestMapping("/")
public class AuthController {
    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    public AuthController(UserService userService, UserAuthenticationProvider userAuthenticationProvider) {
        this.userService = userService;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    /**
     * Endpoint for user login.
     *
     * @return redirects to /events
     */
    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginModel user, HttpSession session) {
        UserDTO authenticatedUser = userService.login(
                user.getEmail(),
                user.getPassword());
        String token = userAuthenticationProvider.createToken(authenticatedUser);
        session.setAttribute("token", token);
        return "redirect:/events";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserLoginModel());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegisterDTO(
                "test@gmail.com", "username", "password"));
        return "register";
    }

    /**
     * Endpoint for user registration.
     *
     * @param userRegisterDTO the UserRegisterDTO object containing user registration data
     * @return login view
     */
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserRegisterDTO userRegisterDTO) {
        userService.register(userRegisterDTO);
        return "redirect:/login";
    }
}
