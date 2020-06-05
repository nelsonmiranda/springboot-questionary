package com.miranda.springboot.springbootquestionary;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miranda.springboot.springbootquestionary.configuration.BasicConfiguration;

@RestController
public class WelcomeController {

	@Autowired
	private WelcomeService welcomeService;
	
	@Autowired
	private BasicConfiguration configuration;
	
	@Value("${welcome.message}")
	private String welcomeMessage;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return welcomeMessage;
	}
	
	@RequestMapping("/dynamic-configuration")
	public Map dynamicConfiguration(){
		Map map = new HashMap();
		
		map.put("value", configuration.isValue());
		map.put("message", configuration.getMessage());
		map.put("number", configuration.getNumber());
		
		return map;
	}
	
}
