package com.michaeltroger;

import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.dictionary.DictionaryImpl;
import com.michaeltroger.options.CloseAppRequestOption;
import com.michaeltroger.optionselection.OptionSelection;
import com.michaeltroger.optionselection.OptionSelectionImpl;
import com.michaeltroger.exception.InvalidInputException;

import java.util.Scanner;

public class Application {

    final Dictionary dict = new DictionaryImpl();
    final OptionSelection optionSelection = new OptionSelectionImpl();

    private boolean isStarted = false;

    public void start() {
        if (isStarted) return;
        isStarted = true;

        try(Scanner in = new Scanner(System.in)) {
            System.out.println("Hello! What can I do for you?\n");
            launchMainLoop(in);
        }
    }

    private void launchMainLoop(Scanner in) {
        while (true) {
            try {
                System.out.println(optionSelection.getOptions());
                var option = optionSelection.create(in.nextLine(), dict);

                System.out.println(option.getInstructions());
                if (option instanceof CloseAppRequestOption) {
                    break;
                }
                System.out.println(option.process(in.nextLine()));
                System.out.println();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
