package com.tosanboom;

/**
 * Serving as the superclass for all exceptions that will be thrown
 * by the Boom API client.
 *
 * @author Ali Dehghani
 */
public abstract class BoomException extends RuntimeException {
    public BoomException() {
        super();
    }

    public BoomException(String message) {
        super(message);
    }

    public BoomException(String message, Throwable cause) {
        super(message, cause);
    }
}