package com.sportcenterplatform.controller;


import com.sportcenterplatform.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class SportsEventController {
    private final SportsEventService sportsEventService;
    @Autowired
    public SportsEventController(SportsEventService sportsEventService) {
        this.sportsEventService = sportsEventService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("events", sportsEventService.getAllSportsEvents());
        return "home";
    }

}
