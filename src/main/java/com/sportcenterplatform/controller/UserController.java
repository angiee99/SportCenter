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
//    private final EventSignupService eventSignupService;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
//        this.eventSignupService = eventSignupService;
        this.userService = userService;
    }

    /**
     * Retrieves the signups of a user by their ID.
     *
     * @param userId The ID of the user whose signups are to be retrieved.
     * @return ResponseEntity containing a list of DTOs representing the signups of the user.
     */
//    @GetMapping("/signups/{userId}")
//    public ResponseEntity<List<EventSignupDTO>> getUserSignups(@PathVariable Long userId) {
//        List<EventSignupDTO> eventSignupDTOS = eventSignupService.getUserSignups(userId);
//
//        if(eventSignupDTOS == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(eventSignupDTOS);
//    }

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
