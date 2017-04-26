package com.tosanboom.accounts;

import com.tosanboom.Asserts;
import com.tosanboom.BoomApi;
import com.tosanboom.Json;
import com.tosanboom.Requests;
import okhttp3.Request;

/**
 * Entry point for all auth related services
 *
 * @author Ali Dehghani
 */
public class Accounts {
    /**
     * Login with a bank account credentials
     *
     * @param bankLoginRequest Encapsulates bank account username/password
     * @param boomApi Encapsulates contextual information for boom API
     * @return A successful bank account login
     * @throws com.tosanboom.RestApiException If REST API return a 4xx/5xx error
     * @throws com.tosanboom.FailedRequestException When we couldn't manage to send the request
     * @throws com.tosanboom.JsonException If we couldn't read/write the JSON to/from a pojo
     * @throws IllegalArgumentException If either of required parameters were {@code null}
     */
    public static BankLoginResponse bankLogin(BankLoginRequest bankLoginRequest, BoomApi boomApi) {
        Asserts.notNull(bankLoginRequest, "bankLoginRequest can't be null");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "auth/login";
        Request request = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(bankLoginRequest))
                .build();

        return Requests.sendRequest(request, BankLoginResponse.class);
    }
}