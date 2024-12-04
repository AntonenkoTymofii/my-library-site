package org.example.MyLibrarySite.controllers;

import lombok.RequiredArgsConstructor;
import org.example.MyLibrarySite.models.AuthenticationRequest;
import org.example.MyLibrarySite.models.UserModel;
import org.example.MyLibrarySite.service.UserService;
import org.example.MyLibrarySite.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    // Маршрут для авторизації (видачі токену)
    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        // Аутентифікація користувача
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Завантаження деталей користувача
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        // Генерація JWT токену
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }

    // Маршрут для сторінки входу
    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    // Маршрут для сторінки реєстрації
    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "registration_page";
    }

    // Маршрут для обробки реєстрації
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserModel user) {
        userService.register(user);
        return "redirect:/login?success";
    }

    // Головна сторінка
    @GetMapping
    public String getHomePage() {
        return "home";
    }
}
