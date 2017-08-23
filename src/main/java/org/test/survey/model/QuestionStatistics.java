package org.test.survey.model;

import java.util.Map;

public class QuestionStatistics {
    private long responsesCount;
    private Map<Long, Double> answersPercentages;

    public QuestionStatistics(long responsesCount, Map<Long, Double> answersPercentages) {
        this.responsesCount = responsesCount;
        this.answersPercentages = answersPercentages;
    }

    public long getResponsesCount() {
        return responsesCount;
    }

    public Map<Long, Double> getAnswersPercentages() {
        return answersPercentages;
    }
}
