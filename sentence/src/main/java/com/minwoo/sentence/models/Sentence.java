package com.minwoo.sentence.models;

import java.util.Map;
import java.util.TreeMap;

public class Sentence {

    // ordered by role so to make easier print the sentence
    private Map<Word.Role,String> words = new TreeMap<>();

    public void add(Word word){
        words.put(word.getRole(),word.getWord());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Word.Role role: words.keySet()){
            sb.append(words.get(role)).append(' ');
        }
        return sb.toString();
    }
}
