package org.test.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.survey.model.AnsweredQuestion;
import org.test.survey.model.CompletedSurvey;
import org.test.survey.model.QuestionStatistics;
import org.test.survey.service.QuestionsService;
import org.test.survey.service.SurveysService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SimpleSurveysService implements SurveysService {
    private final QuestionsService questionsService;
    private final CompletedSurvey completedSurvey = new CompletedSurvey(); //TODO just one for now, extend to have multiple surveys, use a DAO

    @Autowired
    public SimpleSurveysService(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @Override
    public void answerSurveyQuestion(AnsweredQuestion answeredQuestion) {
        completedSurvey.answerQuestion(answeredQuestion);
    }

    @Override
    public QuestionStatistics getQuestionStatistics(Long questionId) {
        final Set<AnsweredQuestion> answeredQuestions = getAnswersForQuestion(questionId);
        long responsesCount = answeredQuestions.size();

        Map<Long, Long> answersCount = getAnswersCountForQuestion(questionId, answeredQuestions);
        Map<Long, Double> answersPercentages = getAnswersPercentagesForQuestion(responsesCount, answersCount);

        return new QuestionStatistics(responsesCount, answersPercentages);
    }

    private Set<AnsweredQuestion> getAnswersForQuestion(Long questionId) {
        Set<AnsweredQuestion> answeredQuestions = new HashSet<>();
        // retrieve the answers for this question
        answeredQuestions.addAll(completedSurvey.getAnsweredQuestions().stream()
                .filter(answeredQuestion -> answeredQuestion.getQuestionId() == questionId)
                .collect(Collectors.toSet()));

        return answeredQuestions;
    }

    private Map<Long, Long> getAnswersCountForQuestion(Long questionId, Set<AnsweredQuestion> answeredQuestions) {
        Map<Long, Long> answersCount = new HashMap<>();

        //build map with all possible answers
        questionsService.getQuestions().get(questionId).getAnswers().forEach(answer -> answersCount.put(answer.getId(), 0L));

        //go through answered questions and increment answer counts
        answeredQuestions.forEach(
                answeredQuestion -> answeredQuestion.getAnswerIds().forEach(
                        answerId -> answersCount.computeIfPresent(answerId, (key, value) -> value + 1)));
        return answersCount;
    }

    private Map<Long, Double> getAnswersPercentagesForQuestion(long responsesCount, Map<Long, Long> answersCount) {
        Map<Long, Double> answersPercentages = new HashMap<>();

        //calculate percentage based on answer count and total count
        answersCount.forEach((key, value) -> answersPercentages.put(key, (((double) value) / responsesCount) * 100));
        return answersPercentages;
    }
}
