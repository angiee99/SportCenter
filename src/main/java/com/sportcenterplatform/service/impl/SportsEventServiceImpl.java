package com.sportcenterplatform.service.impl;

import com.sportcenterplatform.dto.SportEventNewDTO;
import com.sportcenterplatform.dto.SportsEventInfoDTO;
import com.sportcenterplatform.entity.SportsEvent;
import com.sportcenterplatform.repository.SportsEventRepository;
import com.sportcenterplatform.repository.UserRepository;
import com.sportcenterplatform.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportsEventServiceImpl implements SportsEventService {
    SportsEventRepository sportsEventRepository;
    UserRepository userRepository;
    @Autowired
    public SportsEventServiceImpl(SportsEventRepository sportsEventRepository, UserRepository userRepository) {
        this.sportsEventRepository = sportsEventRepository;
        this.userRepository = userRepository;
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

    @Override
    public void save(SportEventNewDTO event) {
        SportsEvent sportsEvent = new SportsEvent();
        sportsEvent.setSportType(event.sportType());
        sportsEvent.setDescription(event.description());
        if(userRepository.findUserById(event.trainerId()).isPresent())
            sportsEvent.setTrainer(userRepository.findUserById(event.trainerId()).get());
        sportsEvent.setIsAvailable(true);

        sportsEventRepository.save(sportsEvent);
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
