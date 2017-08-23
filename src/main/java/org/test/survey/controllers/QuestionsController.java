package org.test.survey.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.test.survey.model.Answer;
import org.test.survey.model.Question;
import org.test.survey.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAutoConfiguration
@RequestMapping("/questions")
public class QuestionsController {
    private QuestionsService questionsService;

    @Autowired
    QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<?> getQuestions() {
        return new ResponseEntity<>(questionsService.getQuestions(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{questionId}", produces = "application/json")
    ResponseEntity<?> getQuestionById(@PathVariable("questionId") Long questionId) {
        return new ResponseEntity<>(questionsService.getQuestions().get(questionId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    ResponseEntity<?> addQuestion(@RequestBody Question question) {
        questionsService.addQuestion(question);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    ResponseEntity<?> editQuestion(@RequestBody Question question) {
        questionsService.updateQuestion(question);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{questionId}")
    ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionsService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{questionId}/answers", consumes = "application/json")
    ResponseEntity<?> addAnswer(@RequestBody Answer answer, @PathVariable("questionId") Long questionId) {
        questionsService.addAnswer(questionId, answer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{questionId}/answers", consumes = "application/json")
    ResponseEntity<?> updateAnswer(@RequestBody Answer answer, @PathVariable("questionId") Long questionId) {
        questionsService.updateAnswer(questionId, answer);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{questionId}/answers/{answerId}")
    ResponseEntity<?> deleteAnswer(@PathVariable("questionId") Long questionId, @PathVariable("answerId") Long answerId) {
        questionsService.deleteAnswer(questionId, answerId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
