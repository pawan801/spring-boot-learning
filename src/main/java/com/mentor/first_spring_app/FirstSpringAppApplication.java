package com.mentor.first_spring_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FirstSpringAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(FirstSpringAppApplication.class, args);
	}

	@GetMapping("/ping")
	public String healthCheck(){
		return "Spring boot";
	}

}
