package com.tosanboom;

public enum Language {
    FARSI("fa_IR"), ENGLISH("en_US");

    private final String locale;

    Language(String locale) {
        this.locale = locale;
    }

    public String locale() {
        return locale;
    }
}