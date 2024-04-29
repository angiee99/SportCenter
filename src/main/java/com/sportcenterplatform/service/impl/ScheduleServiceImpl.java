package com.sportcenterplatform.service.impl;

import com.sportcenterplatform.dto.ScheduleInfoDTO;
import com.sportcenterplatform.entity.Schedule;
import com.sportcenterplatform.repository.ScheduleRepository;
import com.sportcenterplatform.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduleInfoDTO> getAllBySportsEventId(Long id) {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleInfoDTO> result = schedules.stream()
                .map(this::convertToDTO).toList();
        return result;
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
