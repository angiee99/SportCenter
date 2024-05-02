package com.sportcenterplatform.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sportcenterplatform.entity.User;

@Controller
@RequestMapping("/signup")
public class EventSignupController {

    @PostMapping() // with token -> get userId
    public String signup(@AuthenticationPrincipal UserDetails userDetails,
                         @RequestParam(value = "id") Long id){
        Long userId = ((User) userDetails).getId();
        System.out.println("Signup on schedule with id: " + id);
        System.out.println("User Id: " +userId);

        return "test";
    }
}
