package org.example.MyLibrarySite.models;

import lombok.Data;

@Data
public class UserModel {

    private String username;
    private String password;
    private UserRole role;
}