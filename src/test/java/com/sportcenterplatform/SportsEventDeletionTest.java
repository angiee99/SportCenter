package com.sportcenterplatform;

import com.sportcenterplatform.dto.UserRegisterDTO;
import com.sportcenterplatform.entity.Schedule;
import com.sportcenterplatform.entity.SportType;
import com.sportcenterplatform.entity.SportsEvent;
import com.sportcenterplatform.repository.ScheduleRepository;
import com.sportcenterplatform.repository.SportsEventRepository;
import com.sportcenterplatform.repository.UserRepository;
import com.sportcenterplatform.service.EventSignupService;
import com.sportcenterplatform.service.SportsEventService;
import com.sportcenterplatform.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SportsEventDeletionTest {
    private final EventSignupService eventSignupService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final SportsEventRepository sportsEventRepository;
    private final ScheduleRepository scheduleRepository;
    private final SportsEventService sportsEventService;
    @Autowired
    public SportsEventDeletionTest(EventSignupService eventSignupService, UserService userService, UserRepository userRepository, SportsEventRepository sportsEventRepository, ScheduleRepository scheduleRepository, SportsEventService sportsEventService) {
        this.eventSignupService = eventSignupService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.sportsEventRepository = sportsEventRepository;
        this.scheduleRepository = scheduleRepository;
        this.sportsEventService = sportsEventService;
    }

    @Test
    @Transactional
    public void test(){
        // prepare the entities
        UserRegisterDTO user = new UserRegisterDTO("email@domain.com", "coolusername",
                "passWordQ1235");
        Long userId = userService.register(user);

        SportsEvent event1 = new SportsEvent("Testing event", true,
                userRepository.findUserById(userId).get(), SportType.TENNIS);
        Long eventId = sportsEventRepository.save(event1).getId();

        // create a schedule
        Schedule schedule1 = new Schedule( LocalDateTime.of(2024, 1, 1, 13, 0),
                LocalDateTime.of(2024, 1, 1, 15, 0),
                event1, 8);
        Long scheduleId = scheduleRepository.save(schedule1).getId();

        // create a signup for the schedule
        eventSignupService.signup(userId, scheduleId);

        sportsEventService.delete(eventId);

        assertFalse(sportsEventRepository.existsById(eventId));
        assertFalse(scheduleRepository.existsById(scheduleId));
        assertTrue(eventSignupService.getByUserId(userId).isEmpty());
    }
}
