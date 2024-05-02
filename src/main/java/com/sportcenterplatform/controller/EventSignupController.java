package com.sportcenterplatform.controller;

import com.sportcenterplatform.service.EventSignupService;
import com.sportcenterplatform.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final EventSignupService eventSignupService;
    private final ScheduleService scheduleService;
    @Autowired
    public EventSignupController(EventSignupService eventSignupService, ScheduleService scheduleService) {
        this.eventSignupService = eventSignupService;
        this.scheduleService = scheduleService;
    }


    @PostMapping()
    public String signup(@AuthenticationPrincipal UserDetails userDetails,
                         @RequestParam(value = "id") Long scheduleId){
        Long userId = ((User) userDetails).getId();
        System.out.println("Signup on schedule with id: " + scheduleId);
        System.out.println("User Id: " +userId);

        if(scheduleService.isAvailable(scheduleId)){
            eventSignupService.signup(userId, scheduleId);
        }
        else{
            System.out.println("Capacity is full");
        }

        return "test";
    }
}
