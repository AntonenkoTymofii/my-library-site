package org.example.MyLibrarySite.controllers;

import org.example.MyLibrarySite.models.UserModel;
import org.example.MyLibrarySite.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLoginPage() {
        String viewName = homeController.getLoginPage();
        assertEquals("login_page", viewName);
    }

    @Test
    void testGetRegistrationPage() {
        String viewName = homeController.getRegistrationPage(model);

        verify(model).addAttribute(eq("user"), any(UserModel.class));
        assertEquals("registration_page", viewName);
    }

    @Test
    void testRegisterUser() {
        UserModel user = new UserModel();
        user.setUsername("testuser");
        user.setPassword("testpass");

        String redirectView = homeController.registerUser(user);

        verify(userService).register(user);
        assertEquals("redirect:/login?success", redirectView);
    }

    @Test
    void testGetHomePage() {
        String viewName = homeController.getHomePage();
        assertEquals("home", viewName);
    }
}
