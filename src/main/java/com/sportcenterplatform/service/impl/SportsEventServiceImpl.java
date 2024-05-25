package com.sportcenterplatform.service.impl;

import com.sportcenterplatform.dto.SportEventNewDTO;
import com.sportcenterplatform.dto.SportsEventInfoDTO;
import com.sportcenterplatform.entity.Schedule;
import com.sportcenterplatform.entity.SportsEvent;
import com.sportcenterplatform.repository.ScheduleRepository;
import com.sportcenterplatform.repository.SportsEventRepository;
import com.sportcenterplatform.repository.UserRepository;
import com.sportcenterplatform.service.ScheduleService;
import com.sportcenterplatform.service.SportsEventService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportsEventServiceImpl implements SportsEventService {
    private final SportsEventRepository sportsEventRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleService scheduleService;

    @Autowired
    public SportsEventServiceImpl(SportsEventRepository sportsEventRepository, UserRepository userRepository, ScheduleRepository scheduleRepository, ScheduleService scheduleService) {
        this.sportsEventRepository = sportsEventRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleService = scheduleService;
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

    @Override
    @Transactional
    public void delete(Long id) {
        if(sportsEventRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Sports event with id "+ id +" was not found");
        // delete all connected scheduled and signups
        List<Schedule> schedulesToDelete = scheduleRepository
                .getSchedulesBySportsEvent(sportsEventRepository.findById(id).get());

        for(Schedule schedule: schedulesToDelete){
            scheduleService.delete(schedule.getId());
        }

        sportsEventRepository.deleteById(id);
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
