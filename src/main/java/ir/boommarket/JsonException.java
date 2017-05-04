package ir.boommarket;

/**
 * Raised when something went wrong during JSON serialization/de-serialization
 *
 * @author Ali Dehghani
 */
public class JsonException extends BoomException {
    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}