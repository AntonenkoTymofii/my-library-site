package org.example.springlab2.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.example.springlab2.models.UserModel;
import org.example.springlab2.models.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private static final List<UserModel> users = new ArrayList<>();

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        UserModel user = new UserModel();
        user.setRole(UserRole.ADMIN);
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        users.add(user);
    }

    public void register(UserModel user) {
        user.setRole(UserRole.GUEST);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.add(user);
    }

    public UserModel findByLogin(String login) {
        return users.stream().filter(user -> user.getUsername().equals(login))
                .findFirst()
                .orElse(null);
    }
}