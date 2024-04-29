package com.sportcenterplatform.initDB;

import com.sportcenterplatform.entity.*;
import com.sportcenterplatform.repository.ScheduleRepository;
import com.sportcenterplatform.repository.SportsEventRepository;
import com.sportcenterplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Initializes demo data for the application upon startup if enabled.
 * This class implements the {@link ApplicationRunner} interface to execute code
 * after the Spring Boot application context is loaded.
 */
@Component
public class DemoData implements ApplicationRunner {

    private final UserRepository userRepository;
    private final SportsEventRepository sportsEventRepository;
    private final ScheduleRepository scheduleRepository;

    private final PasswordEncoder passwordEncoder;


    @Value("${demoData.enabled}")
    private boolean enabled;

    @Autowired
    public DemoData(UserRepository userRepository, SportsEventRepository sportsEventRepository, ScheduleRepository scheduleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sportsEventRepository = sportsEventRepository;
        this.scheduleRepository = scheduleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Runs the demo data initialization logic upon application startup.
     * This method is called after the Spring Boot application context is loaded.
     *
     * @param args The application arguments passed to the application.
     */
    @Override
    public void run(ApplicationArguments args) {
        // Check if demo data initialization is enabled
        if(!enabled) return;
        // Initialize demo data
        User user1 = new User( "jakethedog@gmail.com","Jake", Role.ADMIN,
                passwordEncoder.encode("iloveladyrainicorn"));
        User user2 = new User( "finnthehuman@gmail.com","Finn", Role.ADMIN,
                passwordEncoder.encode("enchiridion78"));
        User user3 = new User( "bonnibelle@gmail.com", "Bubblegum",Role.USER,
                passwordEncoder.encode("timitimi1"));
        User user4 = new User( "marcythequeen@gmail.com", "Marceline", Role.USER,
                passwordEncoder.encode("dontTakeMyFries"));
        User user5 = new User( "lady@gmail.com", "LadyRainecorn", Role.USER,
                passwordEncoder.encode("speaksKorean"));
        User user6 = new User( "coolguy@gmail.com", "Prismo", Role.USER,
                passwordEncoder.encode("twodimensions2"));
        User user7 = new User( "person1@gmail.com", "John", Role.USER,
                passwordEncoder.encode("normalPassword"));
        User user8 = new User( "notswift@gmail.com", "Taylor", Role.USER,
                passwordEncoder.encode("normalPassword12"));
        User user9 = new User( "anna1989@gmail.com", "Anna", Role.USER,
                passwordEncoder.encode("randomPass9860"));
        User user10 = new User( "artguy@gmail.com", "Mike", Role.USER,
                passwordEncoder.encode("yesterdayTea01"));
        User user11 = new User( "partymonster@gmail.com", "Kegan", Role.USER,
                passwordEncoder.encode("notRealPassword23"));
        User user12 = new User( "randommail@gmail.com", "Josh", Role.USER,
                passwordEncoder.encode("interesting01Pass10"));

        userRepository.saveAll(List.of(user1, user2 ,user3, user4, user5, user6, user7,
                user8, user9, user10, user11, user12));

        SportsEvent event1 = new SportsEvent("Tennis play for kids", true, user2, SportType.TENNIS);
        SportsEvent event2 = new SportsEvent("Tennis play for adults", true, user1, SportType.TENNIS);
        SportsEvent event3 = new SportsEvent("Evening yoga", true, user5, SportType.YOGA);
        SportsEvent event4 = new SportsEvent("Guided TRX full body training", true, user4, SportType.TRX);
        SportsEvent event5 = new SportsEvent("TRX upper body strength", true, user5, SportType.TRX);

        sportsEventRepository.saveAll(List.of(event1, event2, event3, event4, event5));


        Schedule schedule1 = new Schedule( LocalDateTime.of(2024, 4, 12, 13, 0),
                LocalDateTime.of(2024, 4, 12, 15, 0),
                event1, 30);
        Schedule schedule2 = new Schedule( LocalDateTime.of(2024, 5, 12, 16, 0),
                LocalDateTime.of(2024, 5, 15, 18, 0),
                event1, 14);
        Schedule schedule3 = new Schedule( LocalDateTime.of(2024, 5, 5, 16, 30),
                LocalDateTime.of(2024, 5, 5, 18, 0),
                event2, 14);
        Schedule schedule4 = new Schedule( LocalDateTime.of(2024, 5, 14, 16, 30),
                LocalDateTime.of(2024, 5, 14, 18, 0),
                event2, 18);
        Schedule schedule5 = new Schedule( LocalDateTime.of(2024, 5, 1, 18, 30),
                LocalDateTime.of(2024, 5, 1, 18, 45),
                event3, 10);
        Schedule schedule6 = new Schedule( LocalDateTime.of(2024, 5, 3, 17, 0),
                LocalDateTime.of(2024, 5, 3, 19, 15),
                event3, 16);
        Schedule schedule7 = new Schedule( LocalDateTime.of(2024, 5, 2, 9, 0),
                LocalDateTime.of(2024, 5, 2, 10, 30),
                event4, 8);
        Schedule schedule8 = new Schedule( LocalDateTime.of(2024, 5, 24, 16, 30),
                LocalDateTime.of(2024, 5, 14, 18, 0),
                event2, 18);

        scheduleRepository.saveAll(List.of(schedule1, schedule2, schedule3, schedule4,
                schedule5, schedule6, schedule7, schedule8));

    }

}