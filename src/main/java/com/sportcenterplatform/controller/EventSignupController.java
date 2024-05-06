package com.sportcenterplatform.controller;

import com.sportcenterplatform.service.EventSignupService;
import com.sportcenterplatform.service.ScheduleService;
import com.sportcenterplatform.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sportcenterplatform.entity.User;

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
            message = "Successfully signed up!";
            eventSignupService.signup(userId, scheduleId);
        }
        model.addAttribute("message", message);
        model.addAttribute("event", sportsEventService.getSportsEventById(eventId));
        model.addAttribute("schedules", scheduleService.getAllBySportsEventId(
                sportsEventService.getSportsEventEntityById(eventId)));
        return "schedules";
    }
}
