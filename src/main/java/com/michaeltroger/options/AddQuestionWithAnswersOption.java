package com.michaeltroger.options;

import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.entities.DictionaryEntry;
import com.michaeltroger.entities.Answer;
import com.michaeltroger.entities.Question;
import com.michaeltroger.exception.InvalidInputException;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AddQuestionWithAnswersOption implements Option {

    private final Dictionary dict;

    public AddQuestionWithAnswersOption(Dictionary dict) {
        this.dict = dict;
    }

    @Override
    public String getInstructions() {
        return """
                Add a question and answers in the format
                <question>? "<answer1>" "<answer2>" "<answerX>"
                """;
    }

    @Override
    public String process(String input) throws InvalidInputException {
        var entry = parseInput(input);
        dict.addEntry(entry);
        return "Added " + entry + " to dictionary.";
    }

    private DictionaryEntry parseInput(String input) throws InvalidInputException {
        var questionEndIndex = input.indexOf('?');
        var questionAsString = input.substring(0, questionEndIndex + 1);
        var answersAsString = input.substring(questionEndIndex + 1);

        var question = new Question(questionAsString);

        var pattern = Pattern.compile( "\"([^\"]*)\"");
        var matcher = pattern.matcher(answersAsString);
        var answers = Stream
                .generate(() -> matcher.find() ? matcher.group(1) : null)
                .takeWhile(Objects::nonNull)
                .map(Answer::new)
                .toList();

        return new DictionaryEntry(question, answers);
    }
}
