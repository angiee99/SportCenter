package com.sportcenterplatform.service;

import com.sportcenterplatform.dto.ScheduleInfoDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleInfoDTO> getAllBySportsEventId(Long id);
}
