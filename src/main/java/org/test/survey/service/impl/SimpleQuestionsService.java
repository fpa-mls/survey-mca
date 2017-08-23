package org.test.survey.service.impl;

import org.test.survey.model.Answer;
import org.test.survey.model.Question;
import org.test.survey.service.QuestionsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimpleQuestionsService implements QuestionsService {
    private final Map<Long, Question> questions = new HashMap<>(); //TODO use a DAO

    @Override
    public Map<Long, Question> getQuestions() {
        return Collections.unmodifiableMap(questions);
    }

    @Override
    public void addQuestion(Question question) {
        if (questions.containsKey(question.getId())) {
            throw new IllegalArgumentException("Question already exists");
        } else {
            questions.put(question.getId(), question);
        }
    }

    @Override
    public void updateQuestion(Question question) {
        if (questions.containsKey(question.getId())) {
            questions.put(question.getId(), question);
        } else {
            throw new IllegalArgumentException("Question not found");
        }
    }

    @Override
    public void deleteQuestion(Long id) {
        if (questions.containsKey(id)) {
            questions.remove(id);
        } else {
            throw new IllegalArgumentException("Question not found");
        }
    }

    @Override
    public void addAnswer(Long questionId, Answer answer) {
        if (questions.containsKey(questionId)) {
            if (questions.get(questionId).getAnswers().contains(answer)) {
                throw new IllegalArgumentException("Answer already exists");
            } else {
                questions.get(questionId).addAnswer(answer);
            }
        } else {
            throw new IllegalArgumentException("Question not found");
        }
    }

    @Override
    public void updateAnswer(Long questionId, Answer answer) {
        if (questions.containsKey(questionId) && questions.get(questionId).getAnswers().contains(answer)) {
            questions.get(questionId).addAnswer(answer);
        } else {
            throw new IllegalArgumentException("Question not found");
        }
    }

    @Override
    public void deleteAnswer(Long questionId, Long answerId) {
        if (questions.containsKey(questionId) && questions.get(questionId).getAnswers().stream().anyMatch(a -> a.getId() == answerId)) {
            questions.get(questionId).removeAnswer(answerId);
        } else {
            throw new IllegalArgumentException("Question not found");
        }
    }
}