package com.michaeltroger.entities;

import com.michaeltroger.exception.InvalidInputException;

import java.util.List;

public record DictionaryEntry(Question question, List<Answer> answers) {

    public DictionaryEntry {
        if (question == null) {
            throw new InvalidInputException("You need to specify a question.");
        }
        if (answers == null || answers.isEmpty()) {
            throw new InvalidInputException("Provide at least one answer.");
        }
    }
}
