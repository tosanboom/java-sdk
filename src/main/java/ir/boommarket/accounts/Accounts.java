package ir.boommarket.accounts;

import ir.boommarket.Asserts;
import ir.boommarket.BoomApi;
import ir.boommarket.Json;
import ir.boommarket.Requests;
import ir.boommarket.*;
import okhttp3.Request;

/**
 * Entry point for all auth and account related services
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
     * @throws RestApiException If REST API return a 4xx/5xx error
     * @throws FailedRequestException When we couldn't manage to send the request
     * @throws JsonException If we couldn't read/write the JSON to/from a pojo
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

    /**
     * Retrieves personal information of the account owner
     *
     * @param request Encapsulates filtering options available for this service
     * @param boomApi Encapsulates contextual information about the boom API
     * @return An instance of {@linkplain AccountInfo} representing personal information of the
     *         account owner
     * @throws RestApiException If REST API return a 4xx/5xx error
     * @throws FailedRequestException When we couldn't manage to send the request
     * @throws JsonException If we couldn't read/write the JSON to/from a pojo
     * @throws IllegalArgumentException If either of required parameters were {@code null}
     */
    public static AccountInfo getInfo(AccountInfoRequest request, BoomApi boomApi) {
        Asserts.notNull(boomApi, "boomApi can't be null");
        if (request == null) request = AccountInfoRequest.withoutAddress();

        String url = boomApi.baseUrl() + "/accounts" + request.toQueryParam();
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi).url(url).build();

        return Requests.sendRequest(httpRequest, AccountInfo.class);
    }
}