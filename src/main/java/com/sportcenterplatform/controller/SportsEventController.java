package com.sportcenterplatform.controller;


import com.sportcenterplatform.dto.SportEventNewDTO;
import com.sportcenterplatform.entity.SportType;
import com.sportcenterplatform.entity.SportsEvent;
import com.sportcenterplatform.repository.ScheduleRepository;
import com.sportcenterplatform.service.SportsEventService;
import com.sportcenterplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
public class SportsEventController {
    private final SportsEventService sportsEventService;
    private final UserService userService;
    @Autowired
    public SportsEventController(SportsEventService sportsEventService, UserService userService) {
        this.sportsEventService = sportsEventService;
        this.userService = userService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("events", sportsEventService.getAllSportsEvents());
        return "home";
    }
    @GetMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showNewForm(Model model){
        model.addAttribute("event", new SportEventNewDTO(SportType.TENNIS, "", 0L));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("sportTypes", SportType.values());
        return "newEventForm";
    }
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(@ModelAttribute SportEventNewDTO event){
        sportsEventService.save(event);
        return "redirect:/events";
    }
    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@RequestParam(value = "id") Long id){
        sportsEventService.delete(id);
        return "redirect:/events";
    }

}
