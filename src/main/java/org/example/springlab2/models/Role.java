package org.example.springlab2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
public class Role implements GrantedAuthority {
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
