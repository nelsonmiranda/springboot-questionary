package com.miranda.springboot.springbootquestionary.controller;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.springframework.security.crypto.codec.Base64;

import com.miranda.springboot.springbootquestionary.SpringbootQuestionaryApplication;
import com.miranda.springboot.springbootquestionary.model.Question;

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
		headers = createHeadersAuthentication("admin", "password");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	@Test
	void testRetrieveSurveyQuestion() throws Exception {

		String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":"
				+ "\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";

		ResponseEntity<String> response = restTemplate.exchange(createUrl("/surveys/Survey1/questions/Question1"),
				HttpMethod.GET, new HttpEntity<String>(null, headers), String.class);

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void createSurveyQuestion() {
		//Http Body
		Question question = new Question("DOESN'T MATTER", "Small number", "1",
				Arrays.asList("India", "1", "United States", "China"));
		
		ResponseEntity<String> response = restTemplate.exchange(createUrl("/surveys/Survey1/questions"),
				HttpMethod.POST, new HttpEntity<Question>(question, headers), String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		
		assertTrue(actual.contains("/surveys/Survey1/questions"));
	}

	private HttpHeaders createHeadersAuthentication(String username, String password) {
		
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

	private String createUrl(String uri) {
		return "http://localhost:" + port + uri;
	}
	
}
