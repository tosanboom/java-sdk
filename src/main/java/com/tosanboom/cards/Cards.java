package com.tosanboom.cards;

import com.tosanboom.Asserts;
import com.tosanboom.BoomApi;
import com.tosanboom.Json;
import com.tosanboom.Requests;
import okhttp3.Request;

/**
 * Entry point for calling all card related services. Usually each method's first
 * argument is specific to the service itself and the second argument is an instance of
 * {@linkplain BoomApi}.
 *
 * @author Ali Dehghani
 */
public class Cards {
    /**
     * Retrieves balance of the given card
     *
     * @param request Encapsulates the card information
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return An instance of {@linkplain CardBalance} which represents the balance of the given
     * card
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     */
    public static CardBalance getBalance(CardBalanceRequest request, BoomApi boomApi) {
        String url = boomApi.baseUrl() + "cards/" + request.pan + "/balance";
        Request.Builder builder = new Request.Builder();
        Request httpRequest = Requests.withCommonHeaders(builder, boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, CardBalance.class);
    }

    /**
     * List transactions of the given card in the given time-span
     *
     * @param listTransactionsRequest Encapsulates a card transaction list request parameters such as the card credentials,
     *                                time-span filtering options, etc.
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return A list of card transactions of the given card
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     */
    public static CardTransactions listTransactions(ListTransactionsRequest listTransactionsRequest, BoomApi boomApi) {
        Asserts.notNull(listTransactionsRequest, "listTransactionsRequest can't be a null value");
        Asserts.notNull(boomApi, "boomApi can't be null value");

        String url = String.format(boomApi.baseUrl() + "cards/%s/transactions", listTransactionsRequest.pan);
        Request.Builder builder = new Request.Builder();
        Request request = Requests.withCommonHeaders(builder, boomApi)
                .url(url)
                .post(Json.of(listTransactionsRequest))
                .build();

        return Requests.sendRequest(request, CardTransactions.class);
    }
}