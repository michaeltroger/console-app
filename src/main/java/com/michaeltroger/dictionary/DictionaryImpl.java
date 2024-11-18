package com.michaeltroger.dictionary;

import com.michaeltroger.entities.DictionaryEntry;
import com.michaeltroger.entities.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DictionaryImpl implements Dictionary {

    private final List<DictionaryEntry> entries = new ArrayList<>();

    @Override
    public void addEntry(DictionaryEntry entry) {
        entries.add(entry);
    }

    @Override
    public Optional<DictionaryEntry> findQuestion(Question question) {
        return entries.stream()
                .filter(entry -> entry.question().equals(question))
                .findFirst();
    }
}
