package org.example.MyLibrarySite.config;

import org.example.MyLibrarySite.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Налаштування авторизації
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/", "/login", "/registration", "/authenticate").permitAll()
                        .anyRequest().authenticated()
        );

        // Налаштування форми логіну
        http.formLogin(form ->
                form.loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
        );

        // Налаштування логауту
        http.logout(logout -> logout.permitAll());

        // Додавання CSRF (якщо потрібно увімкнути/вимкнути)
        http.csrf(csrf -> csrf.disable()); // Вимкнути CSRF, якщо використовуєте REST API

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public MyUserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
}
