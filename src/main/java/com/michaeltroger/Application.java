package com.michaeltroger;

import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.dictionary.DictionaryImpl;
import com.michaeltroger.entities.OptionSelection;
import com.michaeltroger.options.*;
import com.michaeltroger.exception.InvalidInputException;

import java.io.PrintStream;
import java.util.Scanner;

public class Application {

    private boolean isStarted = false;

    public void start() {
        if (isStarted) return;
        isStarted = true;

        try(Scanner in = new Scanner(System.in)) {
            Dictionary dict = new DictionaryImpl();

            System.out.println("Hello! What can I do for you?\n");
            launchMainLoop(in, dict);
        }
    }

    private void launchMainLoop(Scanner in, Dictionary dict) {
        while (true) {
            try {
                System.out.println(
                        """
                        Available options:
                        0 - To exit the program
                        1 - Ask a specific question
                        2 - Add question and answers
                        Choose a mode to continue:
                        """
                );

                var option = new OptionSelection(in.nextLine(), 0, 2).create(dict);
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
