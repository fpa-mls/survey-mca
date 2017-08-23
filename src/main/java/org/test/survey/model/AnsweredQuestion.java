package org.test.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@JsonIgnoreProperties(value = "id", allowGetters = true)
public class AnsweredQuestion {
    private static AtomicLong sequence = new AtomicLong();

    private final long id;
    private long questionId;
    private Set<Long> answerIds;

    public AnsweredQuestion() {
        this.id = sequence.incrementAndGet();
    }

    public AnsweredQuestion(long id, long questionId, Set<Long> answerIds) {
        this.id = id;
        this.questionId = questionId;
        this.answerIds = answerIds;
    }

    public long getId() {
        return id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public Set<Long> getAnswerIds() {
        return answerIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnsweredQuestion that = (AnsweredQuestion) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
