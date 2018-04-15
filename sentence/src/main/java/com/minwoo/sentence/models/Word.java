package com.minwoo.sentence.models;

public class Word {
    public static enum Role {
        subject,verb,article,adjective,noun;
    }

    private String word;
    private Role role;

    public Word() {
        super();
    }

    public Word(String word) {
        this.word = word;
    }

    public Word(String word, Role role) {
        this.word = word;
        this.role = role;
    }

    public String getWord() {
        return word;
    }

    public String getString() {
        return getWord();
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
