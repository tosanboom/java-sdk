package com.tosanboom;

/**
 * Represents a 4xx or 5xx HTTP response error from our REST API.
 *
 * @author Ali Dehghani
 */
public class RestApiException extends BoomException {
    private final ErrorResponse errorResponse;

    public RestApiException(ErrorResponse errorResponse) {
        super(errorResponse.toString());
        this.errorResponse = errorResponse;
    }

    /**
     * Encapsulates the error code and message corresponding to the failure
     *
     * @return An instance of {@linkplain ErrorResponse}
     */
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}