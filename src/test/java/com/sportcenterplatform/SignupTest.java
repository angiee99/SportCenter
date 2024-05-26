package com.sportcenterplatform;

import com.sportcenterplatform.dto.UserRegisterDTO;
import com.sportcenterplatform.entity.Schedule;
import com.sportcenterplatform.entity.SportType;
import com.sportcenterplatform.entity.SportsEvent;
import com.sportcenterplatform.repository.ScheduleRepository;
import com.sportcenterplatform.repository.SportsEventRepository;
import com.sportcenterplatform.repository.UserRepository;
import com.sportcenterplatform.service.EventSignupService;
import com.sportcenterplatform.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SignupTest {
    private final EventSignupService eventSignupService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final SportsEventRepository sportsEventRepository;
    private final ScheduleRepository scheduleRepository;
    @Autowired
    public SignupTest(EventSignupService eventSignupService, UserService userService, UserRepository userRepository, SportsEventRepository sportsEventRepository, ScheduleRepository scheduleRepository) {
        this.eventSignupService = eventSignupService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.sportsEventRepository = sportsEventRepository;
        this.scheduleRepository = scheduleRepository;
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
        sportsEventRepository.save(event1);

        Schedule schedule1 = new Schedule( LocalDateTime.of(2024, 1, 1, 13, 0),
                LocalDateTime.of(2024, 1, 1, 15, 0),
                event1, 8);
        Long scheduleId = scheduleRepository.save(schedule1).getId();

        // check if isSignedUp is false
        assertFalse(eventSignupService.isSignedUp(userId, scheduleId));

        // signup and check if isSignedUp is true
        eventSignupService.signup(userId, scheduleId);
        assertTrue(eventSignupService.isSignedUp(userId, scheduleId));

        // remove signup and check if isSignedUp is false
        eventSignupService.removeSignup(userId, scheduleId);
        assertFalse(eventSignupService.isSignedUp(userId, scheduleId));
    }
}
