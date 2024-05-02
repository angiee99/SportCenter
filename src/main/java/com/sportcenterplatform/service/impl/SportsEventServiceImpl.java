package com.sportcenterplatform.service.impl;

import com.sportcenterplatform.dto.SportsEventInfoDTO;
import com.sportcenterplatform.entity.SportsEvent;
import com.sportcenterplatform.repository.SportsEventRepository;
import com.sportcenterplatform.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportsEventServiceImpl implements SportsEventService {
    SportsEventRepository sportsEventRepository;
    @Autowired
    public SportsEventServiceImpl(SportsEventRepository sportsEventRepository) {
        this.sportsEventRepository = sportsEventRepository;
    }

    @Override
    public List<SportsEventInfoDTO> getAllSportsEvents() {
        List<SportsEvent> sportsEvents = sportsEventRepository.findAll();
        List<SportsEventInfoDTO> events = sportsEvents.stream()
                .map(this::convertToDTO).toList();
        return events;
    }

    @Override
    public SportsEventInfoDTO getSportsEventById(Long id) {
        SportsEvent sportsEvent = sportsEventRepository.findById(id).orElse(null);
        assert sportsEvent != null;
        return convertToDTO(sportsEvent);
    }

    @Override
    public SportsEvent getSportsEventEntityById(Long id) {
        return sportsEventRepository.findById(id).orElse(null);
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
