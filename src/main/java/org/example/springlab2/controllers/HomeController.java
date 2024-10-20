package org.example.springlab2.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springlab2.models.UserModel;
import org.example.springlab2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "registration_page";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserModel user) {
        userService.register(user);
        return "redirect:/login?success";
    }

    @GetMapping
    public String getHomePage() {
        return "home";
    }
}