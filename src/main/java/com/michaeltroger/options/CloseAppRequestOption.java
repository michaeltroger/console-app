package com.michaeltroger.options;

public class CloseAppRequestOption implements Option {

    @Override
    public String getInstructions() {
        return "Closing the app.";
    }

    @Override
    public String process(String input) {
        return ""; // nothing to do
    }
}
