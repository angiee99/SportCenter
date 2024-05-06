package com.sportcenterplatform.controller;

import com.sportcenterplatform.dto.ScheduleInfoDTO;
import com.sportcenterplatform.entity.EventSignup;
import com.sportcenterplatform.service.EventSignupService;
import com.sportcenterplatform.service.ScheduleService;
import com.sportcenterplatform.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            message = "Successfully signed up!";
            eventSignupService.signup(userId, scheduleId);
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
        List<EventSignup> signups = eventSignupService.getByUserId(userId);

        // todo: change this
        List<ScheduleInfoDTO> schedules = scheduleService.getAllByIds(signups.stream()
                .map(e -> e.getSchedule().getId()).toList());

        model.addAttribute("schedules", schedules);
        return "mySignups";
    }
}
