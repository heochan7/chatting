package com.example.chatting_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChattingApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChattingApplication.class, args);
	}
}
