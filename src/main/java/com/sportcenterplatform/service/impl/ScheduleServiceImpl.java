package com.sportcenterplatform.service.impl;

import com.sportcenterplatform.dto.ScheduleInfoDTO;
import com.sportcenterplatform.dto.ScheduleNewDTO;
import com.sportcenterplatform.entity.Schedule;
import com.sportcenterplatform.entity.SportsEvent;
import com.sportcenterplatform.repository.EventSignupRepository;
import com.sportcenterplatform.repository.ScheduleRepository;
import com.sportcenterplatform.repository.SportsEventRepository;
import com.sportcenterplatform.service.ScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final SportsEventRepository sportsEventRepository;
    private final EventSignupRepository eventSignupRepository;
    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, SportsEventRepository sportsEventRepository, EventSignupRepository eventSignupRepository) {
        this.scheduleRepository = scheduleRepository;
        this.sportsEventRepository = sportsEventRepository;
        this.eventSignupRepository = eventSignupRepository;
    }

    @Override
    public List<ScheduleInfoDTO> getAllBySportsEventId(SportsEvent event) {
        List<Schedule> schedules = scheduleRepository.getSchedulesBySportsEvent(event);
        List<ScheduleInfoDTO> result = schedules.stream()
                .map(this::convertToDTO).toList();
        return result;
    }

    public boolean isAvailable(Long scheduleId){
        if(!scheduleRepository.existsById(scheduleId))
            throw new IllegalArgumentException("Schedule with id "+ scheduleId +" was not found");

        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        return schedule.getSignedUpCount() < schedule.getCapacity();
    }

    @Override
    public void save(ScheduleNewDTO schedule, Long eventId) {
        if(!sportsEventRepository.existsById(eventId))
            throw new IllegalArgumentException("Sports event with id "+ eventId +" was not found");

        Schedule result = new Schedule();
        result.setStartTime(schedule.startTime());
        result.setEndTime(schedule.endTime());
        result.setCapacity(schedule.capacity());
        result.setSportsEvent(sportsEventRepository.findById(eventId).get());
        result.setSignedUpCount(0);

        scheduleRepository.save(result);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(scheduleRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Schedule with id "+ id +" was not found");
        eventSignupRepository.deleteEventSignupBySchedule(scheduleRepository.findById(id).get());
        scheduleRepository.deleteById(id);
    }

    private ScheduleInfoDTO convertToDTO(Schedule schedule){
        ScheduleInfoDTO result = new ScheduleInfoDTO(
                schedule.getId(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getSignedUpCount(),
                schedule.getCapacity()
        );
        return result;
    }
}
