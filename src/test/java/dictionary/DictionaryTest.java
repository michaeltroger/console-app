package dictionary;

import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.dictionary.DictionaryImpl;
import com.michaeltroger.entities.Answer;
import com.michaeltroger.entities.DictionaryEntry;
import com.michaeltroger.entities.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DictionaryTest {

    @Test
    public void testValueNotAvailable() {
        Dictionary dict = new DictionaryImpl();
        var entry = dict.findEntry(new Question("What?"));
        Assertions.assertFalse(entry.isPresent());
    }

    @Test
    public void testValueAvailable() {
        Dictionary dict = new DictionaryImpl();
        var entry = new DictionaryEntry(
                new Question("What?"),
                List.of(new Answer("Tom"))
        );
        dict.addEntry(entry);
        var foundEntry = dict.findEntry(new Question("What?"));
        Assertions.assertTrue(foundEntry.isPresent());
        Assertions.assertEquals(entry, foundEntry.get());
    }
}
