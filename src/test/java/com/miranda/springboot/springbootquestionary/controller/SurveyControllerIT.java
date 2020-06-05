package com.miranda.springboot.springbootquestionary.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.miranda.springboot.springbootquestionary.SpringbootQuestionaryApplication;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = SpringbootQuestionaryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SurveyControllerIT {

	@LocalServerPort
	private int port;
	
	@Test
	void test() {
		System.out.println("PORT: " + port);
	}

}
