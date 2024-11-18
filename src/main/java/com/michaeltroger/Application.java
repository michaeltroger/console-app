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

        try(PrintStream out = System.out;
            Scanner in = new Scanner(System.in)) {
            Dictionary dict = new DictionaryImpl();

            out.println("Hello! What can I do for you?\n");
            launchMainLoop(out, in, dict);
        }
    }

    private void launchMainLoop(PrintStream out, Scanner in, Dictionary dict) {
        while (true) {
            try {
                out.println(
                        """
                        Available options:
                        0 - To exit the program
                        1 - Ask a specific question
                        2 - Add question and answers
                        Choose a mode to continue:
                        """
                );

                var option = new OptionSelection(in.nextLine(), 0, 2).create(dict);
                out.println(option.getInstructions());
                if (option instanceof CloseAppRequestOption) {
                    break;
                }

                out.println(option.process(in.nextLine()));
                out.println();
            } catch (InvalidInputException e) {
                out.println(e.getMessage());
            }
        }
    }
}
