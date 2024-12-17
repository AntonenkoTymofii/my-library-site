package org.example.MyLibrarySite.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
@Tag(name = "Home", description = "Контролер для головної сторінки")
public class HomeApiController {

    @Operation(summary = "Перехід на головну сторінку", description = "Повертає головну сторінку сайту.")
    @GetMapping("/")
    public String home() {
        return "index"; // Thymeleaf шаблон index.html
    }

    @Operation(summary = "Сторінка логіну", description = "Повертає сторінку входу.")
    @GetMapping("/login")
    public String login() {
        return "login"; // Thymeleaf шаблон login.html
    }

    @Operation(summary = "Сторінка реєстрації", description = "Повертає сторінку реєстрації.")
    @GetMapping("/register")
    public String register() {
        return "register"; // Thymeleaf шаблон register.html
    }
}
