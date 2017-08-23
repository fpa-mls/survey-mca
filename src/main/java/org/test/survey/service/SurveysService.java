package org.test.survey.service;

import org.test.survey.model.AnsweredQuestion;
import org.test.survey.model.QuestionStatistics;

public interface SurveysService {
    void answerSurveyQuestion(AnsweredQuestion answeredQuestion);
    QuestionStatistics getQuestionStatistics(Long questionId);
}
