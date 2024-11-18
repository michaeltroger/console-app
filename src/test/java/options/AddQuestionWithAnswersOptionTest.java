package options;

import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.dictionary.DictionaryImpl;
import com.michaeltroger.entities.Question;
import com.michaeltroger.exception.InvalidInputException;
import com.michaeltroger.options.AddQuestionWithAnswersOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddQuestionWithAnswersOptionTest {

    @Test
    public void testNoInput() {
        Dictionary dict = new DictionaryImpl();
        var option = new AddQuestionWithAnswersOption();

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () ->  option.process("", dict)
        );

        Assertions.assertEquals("Question must not be empty", thrown.getMessage());
    }

    @Test
    public void testNoAnswer() {
        Dictionary dict = new DictionaryImpl();
        var option = new AddQuestionWithAnswersOption();

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () ->  option.process("Is it?", dict)
        );

        Assertions.assertEquals("Provide at least one answer.", thrown.getMessage());
    }

    @Test
    public void testNoValidAnswer() {
        Dictionary dict = new DictionaryImpl();
        var option = new AddQuestionWithAnswersOption();

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () ->  option.process("Is it? abc", dict)
        );

        Assertions.assertEquals("Provide at least one answer.", thrown.getMessage());
    }

    @Test
    public void testValidAnswer() {
        Dictionary dict = new DictionaryImpl();
        var option = new AddQuestionWithAnswersOption();

        Assertions.assertDoesNotThrow(
                () ->  option.process("Is it? \"yes\"", dict)
        );

        Assertions.assertEquals("Is it?", dict.findEntry(new Question("Is it?")).get().question().toString());
        Assertions.assertEquals("[yes]", dict.findEntry(new Question("Is it?")).get().answers().toString());
    }
}
