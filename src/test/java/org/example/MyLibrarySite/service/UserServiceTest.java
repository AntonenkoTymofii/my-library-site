package org.example.MyLibrarySite.service;

import org.example.MyLibrarySite.models.UserModel;
import org.example.MyLibrarySite.models.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService.postConstruct(); // Ініціалізуємо користувача admin
    }

    @Test
    void testRegister() {
        UserModel newUser = new UserModel();
        newUser.setUsername("user");
        newUser.setPassword("password");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        userService.register(newUser);

        assertEquals(UserRole.GUEST, newUser.getRole());
        assertEquals("encodedPassword", newUser.getPassword());
        assertNotNull(userService.findByLogin("user"));
        assertEquals("user", userService.findByLogin("user").getUsername());
    }

    @Test
    void testFindByLogin_found() {
        UserModel foundUser = userService.findByLogin("admin");

        assertNotNull(foundUser);
        assertEquals("admin", foundUser.getUsername());
        assertEquals(UserRole.ADMIN, foundUser.getRole());
    }

    @Test
    void testFindByLogin_notFound() {
        UserModel foundUser = userService.findByLogin("unknown");

        assertNull(foundUser);
    }
}
