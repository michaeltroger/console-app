package com.michaeltroger.options;

import com.michaeltroger.entities.Answer;
import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.entities.Question;
import com.michaeltroger.exception.InvalidInputException;

public class AskSpecificQuestionOption implements Option {

    private static final String DEFAULT_ANSWER = "the answer to life, universe and everything is 42";

    private final Dictionary dict;

    public AskSpecificQuestionOption(Dictionary dict) {
        this.dict = dict;
    }

    @Override
    public String getInstructions() {
        return "Enter a specific question:";
    }

    @Override
    public String process(String input) throws InvalidInputException {
        var inputQuestion = new Question(input);
        var foundQuestion = dict.findQuestion(inputQuestion);
        String response;
        if (foundQuestion.isPresent()) {
            response = String.join("\n", foundQuestion.get().answers().stream().map(Answer::text).toList());
        } else {
            response = DEFAULT_ANSWER;
        }
        return response;
    }
}
