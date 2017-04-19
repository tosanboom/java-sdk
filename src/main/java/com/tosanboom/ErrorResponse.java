package com.tosanboom;

/**
 * POJO that encapsulates the error code and message of the error
 * sent from the REST API. The {@linkplain #message} may have a locale-specific
 * value based on the {@linkplain BoomApi#language()} value.
 *
 * @author Ali Dehghani
 */
public class ErrorResponse {
    /**
     * Represents the error code
     */
    private String code;

    /**
     * Represents the error message
     */
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}