package com.sportcenterplatform.initDB;

import com.sportcenterplatform.entity.Role;
import com.sportcenterplatform.entity.User;
import com.sportcenterplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Initializes demo data for the application upon startup if enabled.
 * This class implements the {@link ApplicationRunner} interface to execute code
 * after the Spring Boot application context is loaded.
 */
@Component
public class DemoData implements ApplicationRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Value("${demoData.enabled}")
    private boolean enabled;

    @Autowired
    public DemoData( UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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

    }
}