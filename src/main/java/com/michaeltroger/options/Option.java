package com.michaeltroger.options;

import com.michaeltroger.exception.InvalidInputException;

public interface Option {

    String getInstructions();
    String process(String input) throws InvalidInputException;
}
