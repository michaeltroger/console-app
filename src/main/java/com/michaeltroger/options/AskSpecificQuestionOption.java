package com.michaeltroger.options;

import com.michaeltroger.entities.Answer;
import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.entities.Question;
import com.michaeltroger.exception.InvalidInputException;

public class AskSpecificQuestionOption implements Option {

    private static final String DEFAULT_ANSWER = "the answer to life, universe and everything is 42";

    @Override
    public String getInstructions() {
        return "Enter a specific question:";
    }

    @Override
    public String process(String input, Dictionary dict) throws InvalidInputException {
        var inputQuestion = new Question(input);
        var entry = dict.findEntry(inputQuestion);
        String response;
        if (entry.isPresent()) {
            response = String.join("\n", entry.get().answers().stream().map(Answer::text).toList());
        } else {
            response = DEFAULT_ANSWER;
        }
        return response;
    }
}
