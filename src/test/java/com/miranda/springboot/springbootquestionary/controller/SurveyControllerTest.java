package com.miranda.springboot.springbootquestionary.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.miranda.springboot.springbootquestionary.model.Question;
import com.miranda.springboot.springbootquestionary.service.SurveyService;

@WithMockUser(username = "admin", password = "password")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = SurveyController.class)
class SurveyControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private SurveyService surveyService;
	
	@Test
	public void retrieveDetailsForQuestion() throws Exception {
		
		String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":"
				+ "\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";

		Question question = new Question("Question1", "Largest Country in the World", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));
		
		Mockito.when(surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(question);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void createSurveyQuestion() throws Exception {
		
		Question mockQuestion = new Question("1", "Smallest Number", "1",
				Arrays.asList("1", "2", "3", "4"));

		String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";
		
		//surveyService.addQuestion to respond back with mockQuestion
		Mockito.when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class))).thenReturn(mockQuestion);
		
		//Send question as body to /surveys/Survey1/questions
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/surveys/Survey1/questions/")
				.accept(MediaType.APPLICATION_JSON).content(questionJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		String actual = response.getHeader(HttpHeaders.LOCATION);
		System.out.println("*************** Headers ***************");
		System.out.println(actual);
		assertTrue(actual.contains("/surveys/Survey1/questions"));
		
		
	}
}
