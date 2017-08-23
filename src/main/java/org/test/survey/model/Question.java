package org.test.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@JsonIgnoreProperties(value = "id", allowGetters = true)
public class Question {
    private static AtomicLong sequence = new AtomicLong();

    private final long id;
    private String question;
    private Set<Answer> answers;

    public Question() {
        id = sequence.incrementAndGet();
    }

    public Question(long id, String question, Set<Answer> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void removeAnswer(Long answerId) {
        answers.removeIf(a -> a.getId() == answerId);
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Set<Answer> getAnswers() {
        return Collections.unmodifiableSet(answers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
