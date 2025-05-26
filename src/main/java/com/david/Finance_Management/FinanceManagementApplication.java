package com.david.Finance_Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class FinanceManagementApplication {

	public static void main(String[] args) {
		System.out.println("Current date time: " + LocalDateTime.now());
		SpringApplication.run(FinanceManagementApplication.class, args);
	}

}
