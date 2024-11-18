package com.michaeltroger.options;

import com.michaeltroger.dictionary.Dictionary;

public class CloseAppRequestOption implements Option {

    @Override
    public String getInstructions() {
        return "Closing the app.";
    }

    @Override
    public String process(String input, Dictionary dict) {
        return ""; // nothing to do
    }
}
