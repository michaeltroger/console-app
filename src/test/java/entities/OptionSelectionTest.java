package entities;

import com.michaeltroger.entities.OptionSelection;
import com.michaeltroger.exception.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class OptionSelectionTest {

    @Test
    public void testNullValueNotAllowed() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new OptionSelection(null, 0, 2)
        );

        Assertions.assertEquals("Chosen mode must be a number.", thrown.getMessage());
    }

    @Test
    public void testValueNotWithinRange() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new OptionSelection("3", 0, 2)
        );

        Assertions.assertEquals("Chosen mode must be one of the available options.", thrown.getMessage());
    }

    @Test
    public void testEmptyValueNotAllowed() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new OptionSelection("", 0, 2)
        );

        Assertions.assertEquals("Chosen mode must be a number.", thrown.getMessage());
    }

    @Test
    public void testValueValid() {
        Assertions.assertDoesNotThrow(
                () -> new OptionSelection("2", 0, 2)
        );
    }
}
