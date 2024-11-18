package com.michaeltroger.entities;

import com.michaeltroger.exception.InvalidInputException;

public record Question(String text) {

    private static final int MAX_LENGTH = 255;

    public Question {
        var error = switch (text) {
            case null -> "Question must not be null";
            case String s when s.isEmpty() -> "Question must not be empty";
            case String s when s.length() > MAX_LENGTH -> "Question is too long: " + text.length();
            default -> "";
        };
        if (!error.isEmpty()) {
            throw new InvalidInputException(error);
        }
    }

    @Override
    public String toString() {
        return text;
    }
}
