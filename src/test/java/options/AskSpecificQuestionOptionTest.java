package options;

import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.dictionary.DictionaryImpl;
import com.michaeltroger.entities.Answer;
import com.michaeltroger.entities.DictionaryEntry;
import com.michaeltroger.entities.Question;
import com.michaeltroger.exception.InvalidInputException;
import com.michaeltroger.options.AskSpecificQuestionOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AskSpecificQuestionOptionTest {

    @Test
    public void testNoInput() {
        Dictionary dict = new DictionaryImpl();
        var option = new AskSpecificQuestionOption();

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () ->  option.process("", dict)
        );

        Assertions.assertEquals("Question must not be empty", thrown.getMessage());
    }

    @Test
    public void testDefaultAnswer() {
        Dictionary dict = new DictionaryImpl();
        var option = new AskSpecificQuestionOption();
        var result = option.process("Is it?", dict);

        Assertions.assertEquals("the answer to life, universe and everything is 42", result);
    }

    @Test
    public void testValidAnswer() {
        Dictionary dict = new DictionaryImpl();
        dict.addEntry(new DictionaryEntry(new Question("Is it?"), List.of(new Answer("Tom"), new Answer("Nadine"))));
        var option = new AskSpecificQuestionOption();
        var result = option.process("Is it?", dict);

        Assertions.assertEquals("""
                Tom
                Nadine""", result);
    }
}
