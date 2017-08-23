package org.test.survey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.test.survey.model.AnsweredQuestion;
import org.test.survey.service.SurveysService;

@Controller
@EnableAutoConfiguration
@RequestMapping("/surveys")
public class SurveysController {
    private SurveysService surveysService;

    @Autowired
    SurveysController(SurveysService surveysService) {
        this.surveysService = surveysService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/answerQuestion", consumes = "application/json")
    ResponseEntity<?> answerQuestion(@RequestBody AnsweredQuestion answeredQuestion) {
        surveysService.answerSurveyQuestion(answeredQuestion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/questions/{questionId}", produces = "application/json")
    ResponseEntity<?> getQuestionStatistics(@PathVariable("questionId") Long questionId) {
        return new ResponseEntity<>(surveysService.getQuestionStatistics(questionId), HttpStatus.OK);
    }
}
