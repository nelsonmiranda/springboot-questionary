package com.miranda.springboot.springbootquestionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringbootQuestionaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootQuestionaryApplication.class, args);
	}

	@Profile("dev")
	@Bean
	public String dummy() {
		return "Profile production";
	}
}
