package com.michaeltroger.entities;

import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.exception.InvalidInputException;
import com.michaeltroger.options.AddQuestionWithAnswersOption;
import com.michaeltroger.options.AskSpecificQuestionOption;
import com.michaeltroger.options.CloseAppRequestOption;
import com.michaeltroger.options.Option;

public class OptionSelection {

    private final int mode;

    public OptionSelection(String input, int minAllowed, int maxAllowed) {
        int inputAsNumber;
        try {
            inputAsNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Chosen mode must be a number.");
        }
        boolean isInRange = (inputAsNumber >= minAllowed) && (inputAsNumber <= maxAllowed);
        if (isInRange) {
            mode = inputAsNumber;
        } else {
            throw new InvalidInputException("Chosen mode must be one of the available options.");
        }
    }

    public Option create(Dictionary dict) {
        return switch (mode) {
            case 0 -> new CloseAppRequestOption();
            case 1 -> new AskSpecificQuestionOption(dict);
            case 2 -> new AddQuestionWithAnswersOption(dict);
            default -> throw new IllegalArgumentException("Should never happen since validation happens before.");
        };
    }
}
