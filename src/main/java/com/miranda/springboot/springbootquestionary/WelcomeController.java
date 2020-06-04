package com.miranda.springboot.springbootquestionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@Autowired
	private WelcomeService welcomeService;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return welcomeService.retrieveWelcomeMessage();
	}
}

@Service
class WelcomeService {
	
	public String retrieveWelcomeMessage() {
		return "Good morning updated";
	}
}