package com.tosanboom;

/**
 * Utilities containing the common assertions we will use to make sure that
 * all service pre-conditions are met before actually calling the backend services.
 *
 * @author Ali Dehghani
 */
public class Asserts {
    /**
     * Assert that the given string is not a blank string. If it was a blank string
     * this method will burst with a wonderful {@linkplain IllegalArgumentException}. Otherwise,
     * it would do nothing special.
     *
     * <p>A string is a blank string if it's either a null value or contains any number of whitespaces.
     *
     * @param value The value to assert
     * @param message The message that will be shown if the assertion fails
     * @throws IllegalArgumentException When the given {@code value} is a blank string
     */
    public static void notBlank(String value, String message) {
        notNull(value, message);
        if (value.trim().isEmpty())
            throw new IllegalArgumentException(message);
    }

    /**
     * Assert that the given value is not a null value. If it's not null, this method
     * would do nothing. Otherwise, an {@linkplain IllegalArgumentException} will be thrown
     * with the given {@code message} as the error message.
     *
     * @param value The value to assert it non-nullity
     * @param message The message to display when the value is actually is a null value
     */
    public static void notNull(Object value, String message) {
        if (value == null)
            throw new IllegalArgumentException(message);
    }
}