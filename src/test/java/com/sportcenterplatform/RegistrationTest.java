package com.sportcenterplatform;

import com.sportcenterplatform.dto.UserDTO;
import com.sportcenterplatform.dto.UserRegisterDTO;
import com.sportcenterplatform.service.UserService;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RegistrationTest {
    private final UserService userService;
    @Autowired
    public RegistrationTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    @Transactional
    public void test(){

        UserRegisterDTO user = new UserRegisterDTO("email@domain.com", "coolusername",
                "passWordQ1235");
        Long id = userService.register(user);

        UserDTO checkUser = userService.getUsersById(List.of(id)).get(0);
        assertEquals(checkUser.username(), user.username());

        // test the duplicate username
        UserRegisterDTO user2 = new UserRegisterDTO("email2@domain.com", "coolusername",
                "passWordQ1235");
        assertThrows(Exception.class,()-> userService.register(user2));
    }
}
