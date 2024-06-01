package com.sportcenterplatform.service;

import com.sportcenterplatform.dto.ScheduleAndEventInfoDTO;
import com.sportcenterplatform.dto.ScheduleInfoDTO;
import com.sportcenterplatform.entity.EventSignup;

import java.util.List;

public interface EventSignupService {
    void signup(Long userId, Long scheduleId);
    boolean isSignedUp(Long userId, Long scheduleId);
    List<EventSignup> getByUserId(Long userId);
    List<ScheduleAndEventInfoDTO> getSchedulesByUserId(Long userId);

    void removeSignup(Long userId, Long scheduleId);
}
