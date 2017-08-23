package org.test.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@JsonIgnoreProperties(value = "id", allowGetters = true)
public class Answer {
    private static AtomicLong sequence = new AtomicLong();

    private final long id;
    private String answer;

    public Answer() {
        id = sequence.incrementAndGet();
    }

    public Answer(long id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
