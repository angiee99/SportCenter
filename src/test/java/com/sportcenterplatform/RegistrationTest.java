package com.sportcenterplatform;

import com.sportcenterplatform.service.UserService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegistrationTest {
    private final UserService userService;
    @Autowired
    public RegistrationTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void test(){
        System.out.println(userService.getAllUsers().size());
    }
}
