package com.michaeltroger.optionselection;

import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.exception.InvalidInputException;
import com.michaeltroger.options.AddQuestionWithAnswersOption;
import com.michaeltroger.options.AskSpecificQuestionOption;
import com.michaeltroger.options.CloseAppRequestOption;
import com.michaeltroger.options.Option;

public class OptionSelectionImpl implements OptionSelection {

    private static final int MIN_ALLOWED = 0;
    private static final int MAX_ALLOWED = 2;

    @Override
    public String getOptions() {
        return  """
                        Available options:
                        0 - To exit the program
                        1 - Ask a specific question
                        2 - Add question and answers
                        Choose a mode to continue:
                        """;
    }

    @Override
    public Option create(String input, Dictionary dict) {
        int inputAsNumber;
        try {
            inputAsNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Chosen mode must be a number.");
        }
        boolean isInRange = (inputAsNumber >= MIN_ALLOWED) && (inputAsNumber <= MAX_ALLOWED);
        int mode;
        if (isInRange) {
            mode = inputAsNumber;
        } else {
            throw new InvalidInputException("Chosen mode must be one of the available options.");
        }

        return switch (mode) {
            case 0 -> new CloseAppRequestOption();
            case 1 -> new AskSpecificQuestionOption(dict);
            case 2 -> new AddQuestionWithAnswersOption(dict);
            default -> throw new IllegalArgumentException("Should never happen since validation happens before.");
        };
    }
}
