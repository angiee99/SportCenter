package com.sportcenterplatform.controller;

import com.sportcenterplatform.config.UserAuthenticationProvider;
import com.sportcenterplatform.dto.LoginRequest;
import com.sportcenterplatform.dto.LoginResponse;
import com.sportcenterplatform.dto.UserDTO;
import com.sportcenterplatform.dto.UserRegisterDTO;
import com.sportcenterplatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling authentication-related endpoints.
 */
@RestController
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
     * @param loginRequest the LoginRequest object containing login credentials
     * @return a ResponseEntity containing the JWT token in a LoginResponse object
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UserDTO authenticatedUser = userService.login(loginRequest.email(), loginRequest.password());
        String token = userAuthenticationProvider.createToken(authenticatedUser);
        LoginResponse response = new LoginResponse(token);
        return ResponseEntity.ok(response);
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
