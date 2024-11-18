import com.michaeltroger.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ApplicationTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private InputStream testIn;
    private OutputStream testOut;
    private PrintStream testPrint;

    @AfterEach
    public void tearDown() {
        try {
            testIn.close();
        } catch (IOException _) {
            // Ignore
        }
        try {
            testOut.close();
        } catch (IOException _) {
            // Ignore
        }
        testPrint.close();

        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testEarlyExit() {
        createTestApplication("0").start();

        Assertions.assertEquals(
                """
                        Hello! What can I do for you?
                        
                        Available options:
                        0 - To exit the program
                        1 - Ask a specific question
                        2 - Add question and answers
                        Choose a mode to continue:
                        
                        Closing the app.
                        """,
                testOut.toString()
        );
    }

    @Test
    public void testAnswerNotAvailable() {
        createTestApplication(
                """
                1
                What's your name?
                0
                """).start();

        Assertions.assertEquals(
                """
                        Hello! What can I do for you?
                        
                        Available options:
                        0 - To exit the program
                        1 - Ask a specific question
                        2 - Add question and answers
                        Choose a mode to continue:
                        
                        Enter a specific question:
                        the answer to life, universe and everything is 42
                        
                        Available options:
                        0 - To exit the program
                        1 - Ask a specific question
                        2 - Add question and answers
                        Choose a mode to continue:
                        
                        Closing the app.
                        """,
                testOut.toString()
        );
    }

    @Test
    public void testAnswerAvailable() {
        createTestApplication(
                """
                2
                What's your name? "Tom" "Nadine"
                1
                What's your name?
                0
                """).start();

        Assertions.assertEquals(
                """
                        Hello! What can I do for you?
                        
                        Available options:
                        0 - To exit the program
                        1 - Ask a specific question
                        2 - Add question and answers
                        Choose a mode to continue:
                        
                        Add a question and answers in the format
                        <question>? "<answer1>" "<answer2>" "<answerX>"
                        
                        Added DictionaryEntry[question=What's your name?, answers=[Tom, Nadine]] to dictionary.
                        
                        Available options:
                        0 - To exit the program
                        1 - Ask a specific question
                        2 - Add question and answers
                        Choose a mode to continue:
                        
                        Enter a specific question:
                        Tom
                        Nadine
                        
                        Available options:
                        0 - To exit the program
                        1 - Ask a specific question
                        2 - Add question and answers
                        Choose a mode to continue:
                        
                        Closing the app.
                        """,
                testOut.toString()
        );
    }

    @Test
    public void testUnavailableModeSelected() {
        createTestApplication(
                """
                3
                0
                """).start();

        Assertions.assertEquals(
              """
                      Hello! What can I do for you?
                     
                      Available options:
                      0 - To exit the program
                      1 - Ask a specific question
                      2 - Add question and answers
                      Choose a mode to continue:

                      Chosen mode must be one of the available options.
                      Available options:
                      0 - To exit the program
                      1 - Ask a specific question
                      2 - Add question and answers
                      Choose a mode to continue:
                      
                      Closing the app.
                      """,
                testOut.toString()
        );
    }

    private Application createTestApplication(String input) {
        testIn = new ByteArrayInputStream(input.getBytes());
        testOut = new ByteArrayOutputStream();
        testPrint = new PrintStream(testOut);

        System.setIn(testIn);
        System.setOut(testPrint);

        return new Application();
    }
}
