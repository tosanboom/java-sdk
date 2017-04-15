package com.tosanboom;

public class RequestFailedException extends BoomException {
    private final ErrorResponse errorResponse;

    public RequestFailedException(ErrorResponse errorResponse) {
        super(errorResponse.toString());
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}