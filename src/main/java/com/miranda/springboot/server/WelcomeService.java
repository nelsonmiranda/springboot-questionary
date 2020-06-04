package com.miranda.springboot.server;

import org.springframework.stereotype.Component;

@Component
public class WelcomeService {
	
	public String retrieveWelcomeMessage() {
		return "Good morning updated";
	}
}