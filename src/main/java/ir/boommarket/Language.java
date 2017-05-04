package ir.boommarket;

/**
 * Represents each language that can be used during <em>Content Negotiation</em> process.
 *
 * @see BoomApi.Builder#setLanguage(Language)
 *
 * @author Ali Dehghani
 */
public enum Language {
    FARSI("fa-IR"), ENGLISH("en-US");

    private final String locale;

    Language(String locale) {
        this.locale = locale;
    }

    public String locale() {
        return locale;
    }
}