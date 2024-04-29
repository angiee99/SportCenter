package com.sportcenterplatform.controller;

import com.sportcenterplatform.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
    private final SportsEventService sportsEventService;
    @Autowired
    public ScheduleController(SportsEventService sportsEventService) {
        this.sportsEventService = sportsEventService;
    }

    @GetMapping()
    public String showAllBySportsEventId(@RequestParam(value = "id") Long id, Model model){
        System.out.println(id);
        model.addAttribute("event", sportsEventService.getSportsEventById(id));

        return "schedules";
    }
}
