package com.michaeltroger.dictionary;

import com.michaeltroger.entities.DictionaryEntry;
import com.michaeltroger.entities.Question;

import java.util.Optional;

public interface Dictionary {

    void addEntry(DictionaryEntry entry);
    Optional<DictionaryEntry> findEntry(Question question);
}
