package com.myproject.graduation.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WithMockUser(username = "admin", roles = "ADMIN")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("password123");

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser.getId());
        assertEquals("Test User", createdUser.getName()); // 여기서 문제 발생 가능
        assertTrue(passwordEncoder.matches("password123", createdUser.getPassword())); // 여기서 문제 발생 가능
        assertEquals(UserStatus.ACTIVE, createdUser.getStatus());
    }

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("find@example.com");
        user.setPassword("password123");
        userService.createUser(user);

        User foundUser = userService.findByEmail("find@example.com"); // 여기서 문제 발생 가능
        assertNotNull(foundUser);
        assertEquals("find@example.com", foundUser.getEmail()); // 여기서 문제 발생 가능
    }
}