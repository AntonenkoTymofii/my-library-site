package org.example.springlab2.models;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
