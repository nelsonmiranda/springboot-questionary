package com.miranda.springboot.springbootquestionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.miranda.springboot")
public class SpringbootQuestionaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootQuestionaryApplication.class, args);
	}

}
