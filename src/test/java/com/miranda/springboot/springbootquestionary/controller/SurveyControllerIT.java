package com.miranda.springboot.springbootquestionary.controller;

import java.util.Arrays;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	// Accept : application/json
	@BeforeEach
	public void setupJSONAcceptType() {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	private String createUrl(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	void testRetrieveSurveyQuestion() throws Exception {

		String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";

		ResponseEntity<String> response = restTemplate.exchange(createUrl("/surveys/Survey1/questions/Question1"),
				HttpMethod.GET, new HttpEntity<String>(null, headers), String.class);

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

}
