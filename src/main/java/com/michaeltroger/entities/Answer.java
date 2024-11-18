package com.michaeltroger.entities;

import com.michaeltroger.exception.InvalidInputException;

public record Answer(String text) {

    private static final int MAX_LENGTH = 255;

    public Answer {
        var error = switch (text) {
            case null -> "Answer must not be null";
            case String s when s.isEmpty() -> "Answer must not be empty";
            case String s when s.length() > MAX_LENGTH -> "Answer is too long: " + text.length();
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
