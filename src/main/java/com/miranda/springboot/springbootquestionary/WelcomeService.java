package com.miranda.springboot.springbootquestionary;

import org.springframework.stereotype.Component;

@Component
public class WelcomeService {
	
	public String retrieveWelcomeMessage() {
		return "Good morning!!!";
	}
}