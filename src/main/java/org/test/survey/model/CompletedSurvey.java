package org.test.survey.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class CompletedSurvey {
    private static AtomicLong sequence = new AtomicLong();

    private final long id;
    private final Set<AnsweredQuestion> answeredQuestions;

    public CompletedSurvey() {
        this.id = sequence.incrementAndGet();
        this.answeredQuestions = new HashSet<>();
    }

    public void answerQuestion(AnsweredQuestion answeredQuestion) {
        answeredQuestions.add(answeredQuestion);
    }

    public Set<AnsweredQuestion> getAnsweredQuestions() {
        return answeredQuestions;
    }
}
