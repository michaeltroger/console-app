package entities;

import com.michaeltroger.entities.Question;
import com.michaeltroger.exception.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class QuestionTest {

    @Test
    public void testNullValueNotAllowed() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new Question(null)
        );

        Assertions.assertEquals("Question must not be null", thrown.getMessage());
    }

    @Test
    public void testEmptyValueNotAllowed() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new Question("")
        );

        Assertions.assertEquals("Question must not be empty", thrown.getMessage());
    }

    @Test
    public void testTooLongValue() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new Question("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur finibus odio vitae ipsum convallis, eu ultrices massa vehicula. Morbi semper lorem ac ultrices tristique. Nullam vel felis eu libero suscipit dapibus ut vel est. Mauris odio orci, tristique et lorem nec, faucibus congue mi. Maecenas imperdiet egestas enim, in ultrices eros tincidunt nec. Vivamus bibendum tortor eu orci sollicitudin, sit amet dapibus nunc tempus. Donec venenatis pellentesque nulla, ut varius leo mollis sit amet. Aenean molestie purus sapien, nec rhoncus massa luctus id. Maecenas sagittis dapibus neque.")
        );

        Assertions.assertEquals("Question is too long: 590", thrown.getMessage());
    }

    @Test
    public void testValueValid() {
        Assertions.assertDoesNotThrow(
                () -> new Question("Normal question")
        );
    }
}
