package org.example.springlab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringLab2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringLab2Application.class, args);
	}

}
