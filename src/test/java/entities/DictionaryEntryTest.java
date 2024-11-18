package entities;

import com.michaeltroger.entities.DictionaryEntry;
import com.michaeltroger.entities.Answer;
import com.michaeltroger.entities.Question;
import com.michaeltroger.exception.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class DictionaryEntryTest {

    @Test
    public void testNullValueNotAllowed() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new DictionaryEntry(new Question("What?"), null)
        );

        Assertions.assertEquals("Provide at least one answer.", thrown.getMessage());
    }

    @Test
    public void testEmptyListNotAllowed() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new DictionaryEntry(new Question("What?"), List.of())
        );

        Assertions.assertEquals("Provide at least one answer.", thrown.getMessage());
    }

    @Test
    public void testNoQuestionNotAllowed() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new DictionaryEntry(null, List.of(new Answer("This")))
        );

        Assertions.assertEquals("You need to specify a question.", thrown.getMessage());
    }

    @Test
    public void testValueValid() {
        Assertions.assertDoesNotThrow(
                () -> new DictionaryEntry(new Question("What?"), List.of(new Answer("This")))
        );
    }
}
