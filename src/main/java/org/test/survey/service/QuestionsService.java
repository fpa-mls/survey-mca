package org.test.survey.service;

import org.test.survey.model.Answer;
import org.test.survey.model.Question;

import java.util.Map;

public interface QuestionsService {
    Map<Long, Question> getQuestions();

    void addQuestion(Question question);
    void updateQuestion(Question question);
    void deleteQuestion(Long id);

    void addAnswer(Long questionId, Answer answer);
    void updateAnswer(Long questionId, Answer answer);
    void deleteAnswer(Long questionId, Long answerId);
}
