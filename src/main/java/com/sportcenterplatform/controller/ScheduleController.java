package com.sportcenterplatform.controller;

import com.sportcenterplatform.entity.SportsEvent;
import com.sportcenterplatform.service.ScheduleService;
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
    private final ScheduleService scheduleService;
    @Autowired
    public ScheduleController(SportsEventService sportsEventService, ScheduleService scheduleService) {
        this.sportsEventService = sportsEventService;
        this.scheduleService = scheduleService;
    }

    @GetMapping()
    public String showAllBySportsEventId(@RequestParam(value = "id") Long id, Model model){
        System.out.println(id);
        model.addAttribute("event", sportsEventService.getSportsEventById(id));
        model.addAttribute("schedules", scheduleService.getAllBySportsEventId(
                sportsEventService.getSportsEventEntityById(id)));
        return "schedules";
    }
}
