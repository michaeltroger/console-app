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
        var option = new AddQuestionWithAnswersOption(dict);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () ->  option.process("")
        );

        Assertions.assertEquals("Question must not be empty", thrown.getMessage());
    }

    @Test
    public void testNoAnswer() {
        Dictionary dict = new DictionaryImpl();
        var option = new AddQuestionWithAnswersOption(dict);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () ->  option.process("Is it?")
        );

        Assertions.assertEquals("Provide at least one answer.", thrown.getMessage());
    }

    @Test
    public void testNoValidAnswer() {
        Dictionary dict = new DictionaryImpl();
        var option = new AddQuestionWithAnswersOption(dict);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () ->  option.process("Is it? abc")
        );

        Assertions.assertEquals("Provide at least one answer.", thrown.getMessage());
    }

    @Test
    public void testValidAnswer() {
        Dictionary dict = new DictionaryImpl();
        var option = new AddQuestionWithAnswersOption(dict);

        Assertions.assertDoesNotThrow(
                () ->  option.process("Is it? \"yes\"")
        );

        Assertions.assertEquals("Is it?", dict.findEntry(new Question("Is it?")).get().question().toString());
        Assertions.assertEquals("[yes]", dict.findEntry(new Question("Is it?")).get().answers().toString());
    }
}
