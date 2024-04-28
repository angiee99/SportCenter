package com.sportcenterplatform.controller;

import com.sportcenterplatform.config.UserAuthenticationProvider;
import com.sportcenterplatform.dto.*;
import com.sportcenterplatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private final TokenStorage tokenStorage;

    @Autowired
    public AuthController(UserService userService, UserAuthenticationProvider userAuthenticationProvider, TokenStorage tokenStorage) {
        this.userService = userService;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.tokenStorage = tokenStorage;
    }

    /**
     * Endpoint for user login.
     *
     * @return a ResponseEntity containing the JWT token in a LoginResponse object
     */
    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginModel user, Model model) {
        UserDTO authenticatedUser = userService.login(
                user.getEmail(),
                user.getPassword());
        String token = userAuthenticationProvider.createToken(authenticatedUser);
        tokenStorage.setToken(token);
        return "redirect:/events";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserLoginModel());
        return "login";
    }

    /**
     * Endpoint for user registration.
     *
     * @param userRegisterDTO the UserRegisterDTO object containing user registration data
     * @return a ResponseEntity containing a message confirming the registration
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        Long id = userService.register(userRegisterDTO);
        return ResponseEntity.ok("User with id " + id + " successfully registered");
    }
}
