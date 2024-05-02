package com.sportcenterplatform.service.impl;

import com.sportcenterplatform.entity.EventSignup;
import com.sportcenterplatform.entity.Schedule;
import com.sportcenterplatform.repository.EventSignupRepository;
import com.sportcenterplatform.repository.ScheduleRepository;
import com.sportcenterplatform.repository.UserRepository;
import com.sportcenterplatform.service.EventSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventSignupServiceImpl implements EventSignupService {
    private final EventSignupRepository eventSignupRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    @Autowired
    public EventSignupServiceImpl(EventSignupRepository eventSignupRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.eventSignupRepository = eventSignupRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void signup(Long userId, Long scheduleId) {
        if(!userRepository.existsById(userId))
            throw new IllegalArgumentException("User with id "+ userId +" was not found");
        if(!scheduleRepository.existsById(scheduleId))
            throw new IllegalArgumentException("Schedule with id "+ scheduleId +" was not found");

        // TODO control if not signed up already

        EventSignup signup = new EventSignup(LocalDateTime.now(),
                userRepository.findById(userId).get(), scheduleRepository.findById(scheduleId).get());

        eventSignupRepository.save(signup);

        increaseSignUpCount(scheduleId);
    }

    private void increaseSignUpCount(Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        schedule.setSignedUpCount(schedule.getSignedUpCount() + 1);
        scheduleRepository.save(schedule);
    }

}
