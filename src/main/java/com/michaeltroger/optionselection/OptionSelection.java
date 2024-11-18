package com.michaeltroger.optionselection;

import com.michaeltroger.dictionary.Dictionary;
import com.michaeltroger.options.Option;

public interface OptionSelection {

    String getOptions();
    Option create(String input, Dictionary dict);
}
