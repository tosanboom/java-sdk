package com.tosanboom.deposits;

import com.tosanboom.Asserts;
import com.tosanboom.BoomApi;
import com.tosanboom.Json;
import com.tosanboom.Requests;
import okhttp3.Request;

/**
 * Entry point for calling all deposit related services. Usually each method's first
 * argument is specific to the service itself and the second argument is an instance of
 * {@linkplain BoomApi}.
 *
 * @author Marjan Mehranfar
 */
public class Deposits {
    /**
     * Get details information of a given {@code depositNumber}
     *
     * @param depositNumber The deposit number
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return An instance of {@linkplain Deposit} which represents the information of the given {@code depositNumber}
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     */
    public static Deposit getDetail(String depositNumber, BoomApi boomApi) {
        if (depositNumber == null || depositNumber.trim().isEmpty())
            throw new IllegalArgumentException("DepositNumber must not be null or a blank string");

        String url = boomApi.baseUrl() + "deposits/" + depositNumber;
        Request.Builder builder = new Request.Builder();
        Request httpRequest = Requests.withCommonHeaders(builder, boomApi)
                .url(url)
                .get()
                .build();

        return Requests.sendRequest(httpRequest, Deposit.class);
    }

    /**
     * Return owner name of the given {@code depositNumber}
     *
     * @param depositNumber The number of deposit
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return An instance of {@linkplain DepositHolder} that represents owner name of the given {@code depositNumber}
     */
    public static DepositHolder getHolder(String depositNumber, BoomApi boomApi) {
        if (depositNumber == null || depositNumber.trim().isEmpty())
            throw new IllegalArgumentException("DepositNumber must not be null or a blank string");

        String url = boomApi.baseUrl() + "deposits/" + depositNumber + "/holder";
        Request.Builder builder = new Request.Builder();
        Request httpRequest = Requests.withCommonHeaders(builder, boomApi)
                .url(url)
                .get()
                .build();

        return Requests.sendRequest(httpRequest, DepositHolder.class);
    }

    /**
     * Batch transfer to given destinations encapsulated in the given {@linkplain BatchTransferRequest}
     *
     * @param request It has required and optional parameters to batch transfer from a source deposit number
     * to one or many destination deposit numbers
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return An instance of {@linkplain BatchTransfer} that represents the response of each transfer
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException If either of parameters were {@code null}
     */
    public static BatchTransfer batchTransfer(BatchTransferRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "The request parameter can not be null");
        Asserts.notNull(boomApi, "The boomApi parameter can not be null");

        String url = boomApi.baseUrl() + "deposits/transfer/batch";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                                      .url(url)
                                      .post(Json.of(request))
                                      .build();

        return Requests.sendRequest(httpRequest, BatchTransfer.class);
    }
}