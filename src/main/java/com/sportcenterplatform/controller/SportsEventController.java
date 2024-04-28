package com.sportcenterplatform.controller;


import com.sportcenterplatform.dto.SportsEventInfoDTO;
import com.sportcenterplatform.entity.SportsEvent;
import com.sportcenterplatform.repository.SportsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class SportsEventController {
    private final SportsEventRepository sportsEventRepository;
    @Autowired
    public SportsEventController(SportsEventRepository sportsEventRepository) {
        this.sportsEventRepository = sportsEventRepository;
    }

    @GetMapping()
    public String showAll(Model model) {
        List<SportsEvent> sportsEvents = sportsEventRepository.findAll();
        List<SportsEventInfoDTO> events = sportsEvents.stream()
                .map(this::convertToDTO).toList();

        model.addAttribute("events", events);
        return "home";
    }


    private SportsEventInfoDTO convertToDTO(SportsEvent event){
        return new SportsEventInfoDTO(
                event.getId(),
                event.getSportType(),
                event.getIsAvailable(),
                event.getDescription(),
                event.getTrainer().getUsername(),
                event.getTrainer().getEmail()
        );
    }
}
