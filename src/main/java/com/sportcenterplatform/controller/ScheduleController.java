package com.sportcenterplatform.controller;

import com.sportcenterplatform.dto.ScheduleNewDTO;
import com.sportcenterplatform.dto.SportEventNewDTO;
import com.sportcenterplatform.entity.SportType;
import com.sportcenterplatform.service.ScheduleService;
import com.sportcenterplatform.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    @GetMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showNewForm(@RequestParam(value = "eventId") Long eventId, Model model){
        model.addAttribute("event", sportsEventService.getSportsEventById(eventId));
        model.addAttribute("schedule", new ScheduleNewDTO(LocalDateTime.now(), LocalDateTime.now(), 10));

        return "newScheduleForm";
    }
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(@RequestParam(value = "eventId") Long eventId,
                       @ModelAttribute ScheduleNewDTO schedule){

        scheduleService.save(schedule, eventId);
        return "redirect:/schedules?id="+ eventId;
    }
}
