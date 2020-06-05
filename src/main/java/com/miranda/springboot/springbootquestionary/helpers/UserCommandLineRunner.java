package com.miranda.springboot.springbootquestionary.helpers;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.miranda.springboot.springbootquestionary.entity.User;
import com.miranda.springboot.springbootquestionary.repository.UserRepository;



@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private static final Logger log =  LoggerFactory.getLogger(UserCommandLineRunner.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("Admin", "Admin"));
		userRepository.save(new User("Ranga", "Admin"));
		userRepository.save(new User("Ravi", "User"));
		userRepository.save(new User("Satish", "Admin"));
		userRepository.save(new User("Raghu", "User"));

		for (User user : userRepository.findAll()) {
			log.info(user.toString());
		}
	}

}
