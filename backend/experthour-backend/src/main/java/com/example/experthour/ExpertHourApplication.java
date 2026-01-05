package com.example.experthour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class ExpertHourApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExpertHourApplication.class, args);
	}
}
