package org.yeditepe.week10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yeditepe.week10.entity.User;
import org.yeditepe.week10.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    void shouldRegisterUserSuccessfully() {
        User user = userService.registerUser("Ali", "ali@test.com");

        assertNotNull(user.getId());
        assertEquals("Ali", user.getName());
    }

    @Test
    void shouldNotAllowDuplicateEmail() {
        userService.registerUser("Ali", "duplicate@test.com");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser("Veli", "duplicate@test.com");
        });

        assertEquals("Email already exists", exception.getMessage());
    }

    @Test
    void shouldFindUserByEmail() {
        userService.registerUser("Ayşe", "ayse@test.com");

        User found = userService.getUserByEmail("ayse@test.com");

        assertNotNull(found);
        assertEquals("Ayşe", found.getName());
    }
}