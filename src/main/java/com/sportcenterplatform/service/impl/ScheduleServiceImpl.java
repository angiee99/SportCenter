package com.sportcenterplatform.service.impl;

import com.sportcenterplatform.dto.ScheduleInfoDTO;
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

        return null;
    }
}
