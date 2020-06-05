package com.miranda.springboot.springbootquestionary.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.miranda.springboot.springbootquestionary.model.Question;
import com.miranda.springboot.springbootquestionary.service.SurveyService;

@RestController
public class SurveyController {

	@Autowired
	private SurveyService surveyService;
	
	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveQuestionsFromSurvey(@PathVariable String surveyId){
		return surveyService.retrieveQuestions(surveyId);
	}
	
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForQuestion(@PathVariable String surveyId, @PathVariable String questionId){
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
	
	/**
	//What should be structure of request body?
		{
	        "id": "Question1",
	        "description": "Largest Country in the World",
	        "correctAnswer": "Russia",
	        "options": [
	            "India",
	            "Russia",
	            "United States",
	            "China"
	        ]
	    }
	//How will it be mapped to Question object? 
	 * Using @RequestBody
	//What should be returned?
	//What should be response status?
	 * 
	 */
	
	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionToSurvey(@PathVariable String surveyId,@RequestBody Question newQuestion){
		
		Question question = surveyService.addQuestion(surveyId, newQuestion);
		
		if(question == null)
			return ResponseEntity.noContent().build();
	
		//Success - URI of the new resource in Response Header
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{questionId}").buildAndExpand(question.getId()).toUri();
		
		//Status - Created
		return ResponseEntity.created(location).build(); 
	}
	
}
