package optionselection;

import com.michaeltroger.optionselection.OptionSelectionImpl;
import com.michaeltroger.exception.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class OptionSelectionTest {

    @Test
    public void testNullValueNotAllowed() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new OptionSelectionImpl().create(null, null)
        );

        Assertions.assertEquals("Chosen mode must be a number.", thrown.getMessage());
    }

    @Test
    public void testValueNotWithinRange() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new OptionSelectionImpl().create("3", null)
        );

        Assertions.assertEquals("Chosen mode must be one of the available options.", thrown.getMessage());
    }

    @Test
    public void testEmptyValueNotAllowed() {
        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> new OptionSelectionImpl().create("", null)
        );

        Assertions.assertEquals("Chosen mode must be a number.", thrown.getMessage());
    }

    @Test
    public void testValueValid() {
        Assertions.assertDoesNotThrow(
                () -> new OptionSelectionImpl().create("2", null)
        );
    }
}
