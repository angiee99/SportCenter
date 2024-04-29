package com.sportcenterplatform.controller;

import com.sportcenterplatform.service.UserService;
import com.sportcenterplatform.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller class for managing sport user-related endpoints.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Retrieves all users.
     *
     * @return ResponseEntity containing a list of DTOs representing all users.
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
        List<UserInfoDTO> usersInfo = userService.getAllUsers();
        return ResponseEntity.ok(usersInfo);
    }
}
