package com.example.sanity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SanityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanityApplication.class, args);
	}

}
