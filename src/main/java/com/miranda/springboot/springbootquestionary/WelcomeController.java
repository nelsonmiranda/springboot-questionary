package com.miranda.springboot.springbootquestionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@Autowired
	private WelcomeService welcomeService;
	
	@Value("${welcome.message}")
	private String welcomeMessage;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return welcomeMessage;
	}
}
