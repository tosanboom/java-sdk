package com.tosanboom;

/**
 * Raised when we couldn't send the HTTP request to the server
 *
 * @author Ali Dehghani
 */
public class FailedRequestException extends BoomException {
    public FailedRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}