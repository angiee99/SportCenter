package com.sportcenterplatform.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class SportsEventController {
    @GetMapping()
    public String login(Model model) {
//        model.addAttribute("events", allEvents);
        return "home";
    }
}
