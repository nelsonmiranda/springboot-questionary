package com.miranda.springboot.springbootquestionary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
	
}
