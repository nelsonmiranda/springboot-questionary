package com.miranda.springboot.springbootquestionary.controller;


import java.util.Arrays;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.miranda.springboot.springbootquestionary.SpringbootQuestionaryApplication;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = SpringbootQuestionaryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SurveyControllerIT {

	@LocalServerPort
	private int port;
	
	@Test
	void testRetrieveSurveyQuestion() throws Exception {
		
		String url = "http://localhost:" + port + "/surveys/Survey1/questions/Question1";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		
		//Accept : application/json
		
		//String output = restTemplate.getForObject(url, String.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));	
		
		//HttpEntity
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		
		System.out.println("*********** Response ********************");
		System.out.println("Response: " + response.getBody());
		
		String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";
		
        JSONAssert.assertEquals(expected, response.getBody(), false);
	}

}
