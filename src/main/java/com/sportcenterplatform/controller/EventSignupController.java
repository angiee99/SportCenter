package com.sportcenterplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signup")
public class EventSignupController {

    @PostMapping() // with token -> get userId
    public String signup(@RequestParam(value = "id") Long id){
        System.out.println("Signup on schedule with id: " + id);
        return "test";
    }
}
