package org.test.survey.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.test.survey.model.Answer;
import org.test.survey.model.AnsweredQuestion;
import org.test.survey.model.Question;
import org.test.survey.model.QuestionStatistics;
import org.test.survey.service.QuestionsService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.when;

public class SimpleSurveysServiceTest {

    @Mock
    private QuestionsService questionsService;

    private SimpleSurveysService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new SimpleSurveysService(questionsService);
    }

    @Test
    public void testGetStatistics() {
        Set<Long> answerIds1 = new HashSet<>();
        answerIds1.add(1L);

        Set<Long> answerIds2 = new HashSet<>();
        answerIds1.add(2L);

        Map<Long, Question> questions = new HashMap<>();
        Set<Answer> answers = new HashSet<>();

        answers.add(new Answer(1, "12"));
        answers.add(new Answer(2, "42"));
        answers.add(new Answer(3, "100"));
        questions.put(1L, new Question(1L, "What?", answers));

        when(questionsService.getQuestions()).thenReturn(questions);

        service.answerSurveyQuestion(new AnsweredQuestion(1L, 1L, answerIds1));
        service.answerSurveyQuestion(new AnsweredQuestion(2L, 1L, answerIds2));
        QuestionStatistics questionStatistics = service.getQuestionStatistics(1L);

        Assert.assertEquals(2, questionStatistics.getResponsesCount());
        Assert.assertEquals(50.0, questionStatistics.getAnswersPercentages().get(1L), 0.01);
        Assert.assertEquals(50.0, questionStatistics.getAnswersPercentages().get(2L), 0.01);
        Assert.assertEquals(0.0, questionStatistics.getAnswersPercentages().get(3L), 0.01);

    }

    //TODO write more tests
}
