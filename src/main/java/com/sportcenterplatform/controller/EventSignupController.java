package com.sportcenterplatform.controller;

import com.sportcenterplatform.dto.ScheduleAndEventInfoDTO;
import com.sportcenterplatform.dto.ScheduleInfoDTO;
import com.sportcenterplatform.service.EventSignupService;
import com.sportcenterplatform.service.ScheduleService;
import com.sportcenterplatform.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sportcenterplatform.entity.User;

import java.util.List;

@Controller
@RequestMapping("/signup")
public class EventSignupController {
    private final EventSignupService eventSignupService;
    private final ScheduleService scheduleService;
    private final SportsEventService sportsEventService;
    @Autowired
    public EventSignupController(EventSignupService eventSignupService, ScheduleService scheduleService, SportsEventService sportsEventService) {
        this.eventSignupService = eventSignupService;
        this.scheduleService = scheduleService;
        this.sportsEventService = sportsEventService;
    }


    @PostMapping()
    public String signup(@AuthenticationPrincipal UserDetails userDetails,
                         @RequestParam(value = "id") Long scheduleId,
                         @RequestParam(value = "eventId") Long eventId,
                         Model model){
        Long userId = ((User) userDetails).getId();
        String message;
        if(!scheduleService.isAvailable(scheduleId)){
            message = "Capacity for selected schedule is full";
        }
        else if(eventSignupService.isSignedUp(userId, scheduleId)){
            message = "You are already signed up for this schedule";
        }
        else{
            eventSignupService.signup(userId, scheduleId);
            message = "Successfully signed up!";
        }
        model.addAttribute("message", message);
        model.addAttribute("event", sportsEventService.getSportsEventById(eventId));
        model.addAttribute("schedules", scheduleService.getAllBySportsEventId(
                sportsEventService.getSportsEventEntityById(eventId)));
        return "schedules";
    }
    @GetMapping()
    public String getAll(@AuthenticationPrincipal UserDetails userDetails,
                         Model model){
        Long userId = ((User) userDetails).getId();
        List<ScheduleAndEventInfoDTO> schedules = eventSignupService.getSchedulesByUserId(userId);
        model.addAttribute("schedules", schedules);
        return "mySignups";
    }
    @PostMapping("/delete")
    public String deleteSignup(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam(value = "id") Long scheduleId){
        Long userId = ((User) userDetails).getId();
        eventSignupService.removeSignup(userId, scheduleId);
        return "redirect:/signup";
    }

}
