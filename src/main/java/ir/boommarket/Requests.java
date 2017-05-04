package ir.boommarket;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Utilities for preparing, sending and parsing HTTP requests and their responses
 *
 * @author Ali Dehghani
 */
public class Requests {
    private static final String SANDBOX_HEADER = "Sandbox";
    private static final String BANK_HEADER = "Bank-Id";
    private static final String SESSION_HEADER = "Session";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String APP_KEY_HEADER = "App-Key";
    private static final String TOKEN_HEADER = "Token-Id";
    private static final String DEVICE_ID_HEADER = "Device-Id";
    private static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";

    /**
     * Adding common headers which will be used for almost all REST API calls
     *
     * @param builder The request to be decorated
     * @param boomApi Encapsulates those headers to be added to the request
     * @return Same {@linkplain okhttp3.Request.Builder} with headers attached to it
     */
    public static Request.Builder withCommonHeaders(Request.Builder builder, BoomApi boomApi) {
        if (boomApi == null) return builder;

        builder.header(SANDBOX_HEADER, boomApi.isSandbox() + "");
        if (boomApi.bank() != null) builder.header(BANK_HEADER, boomApi.bank().swiftCode());
        if (notBlank(boomApi.session())) builder.header(SESSION_HEADER, boomApi.session());
        if (notBlank(boomApi.accessToken())) builder.header(AUTHORIZATION_HEADER, "Bearer " + boomApi.accessToken());
        if (notBlank(boomApi.appKey())) builder.header(APP_KEY_HEADER, boomApi.appKey());
        if (notBlank(boomApi.boomToken())) builder.header(TOKEN_HEADER, boomApi.boomToken());
        if (notBlank(boomApi.deviceId())) builder.header(DEVICE_ID_HEADER, boomApi.deviceId());
        if (boomApi.language() != null) builder.header(ACCEPT_LANGUAGE_HEADER, boomApi.language().locale());

        return builder;
    }

    /**
     * Sending the http request and processing the http response
     *
     * <p>First off, will'll execute the passed {@code request}. If the http status
     * code for response was a 2xx one, we try to read the returned JSON as a type of
     * {@code clazz}. Otherwise an exception encapsulating the error will be thrown.
     *
     * @param request The request to execute
     * @param clazz Encapsulates type information of the successful response pojo
     * @param <T> The type of a successful response which varies based on the service itself.
     * @return An instance of {@linkplain T} representing a successful response
     * @throws RestApiException When a 4xx or 5xx error returns from the REST API
     * @throws FailedRequestException When we couldn't send the http request
     * @throws JsonException When we couldn't parse the JSON response
     */
    public static <T> T sendRequest(Request request, Class<T> clazz) {
        try {
            Response response = HttpClient.getInstance().newCall(request).execute();
            String responseBody = response.body().string();
            if (response.isSuccessful()) {
                return Json.read(responseBody, clazz);
            }

            ErrorResponse errorResponse = Json.read(responseBody, ErrorResponse.class);
            throw new RestApiException(errorResponse);
        } catch (IOException e) {
            throw new FailedRequestException("Couldn't send the http request", e);
        }
    }

    private static boolean notBlank(String whatever) {
        return whatever != null && !whatever.trim().isEmpty();
    }
}