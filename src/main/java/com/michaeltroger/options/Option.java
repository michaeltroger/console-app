package com.michaeltroger.options;

import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.exception.InvalidInputException;

public interface Option {

    String getInstructions();
    String process(String input, Dictionary dict) throws InvalidInputException;
}
