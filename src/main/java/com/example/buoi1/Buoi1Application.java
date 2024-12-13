package com.example.buoi1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.buoi1") // Ensure it scans the correct base package
public class Buoi1Application {

	public static void main(String[] args) {
		SpringApplication.run(Buoi1Application.class, args);
	}
}
