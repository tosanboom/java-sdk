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
 * @author Marjan Mehranfar
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
     * @throws IllegalArgumentException When one of the required parameters were {@code null}
     */
    public static CardBalance getBalance(CardBalanceRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be a null value");
        Asserts.notNull(boomApi, "boomApi can't be a null value");

        String url = boomApi.baseUrl() + "cards/" + request.pan + "/balance";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, CardBalance.class);
    }

    /**
     * Returns personal information of a card identified by {@code pan} or {@code destinationPan}
     *
     * @param request Encapsulates credential's information of card
     * @param boomApi  Encapsulates header's parameters that must be send to request service,
     *                 and default address url.
     * @return The response of calling service {@link CardHolder}
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException When one of the required parameters were {@code null}
     */
    public static CardHolder getHolder(CardHolderRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be null");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "cards/" + request.pan + "/holder";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, CardHolder.class);
    }

    /**
     * Receive list of cards for current authenticated user
     *
     * @param request Has some parameters to filter the received list, like pan number, length, offset, etc.
     * @param boomApi Encapsulated essential and optional headers for request to service
     * @return List of cards that each item of this list has some information about {@link CardList.Card}
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException When the {@code boomApi} is {@code null}
     */
    public static CardList getCards(CardListRequest request, BoomApi boomApi) {
        Asserts.notNull(boomApi, "boomApi can't be null");
        if (request == null) request = CardListRequest.withoutFilter();

        String url = boomApi.baseUrl() + "cards/";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, CardList.class);
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
     * @throws IllegalArgumentException When one of the required parameters were {@code null}
     */
    public static CardTransactions listTransactions(ListTransactionsRequest listTransactionsRequest, BoomApi boomApi) {
        Asserts.notNull(listTransactionsRequest, "listTransactionsRequest can't be a null value");
        Asserts.notNull(boomApi, "boomApi can't be null value");

        String url = String.format(boomApi.baseUrl() + "cards/%s/transactions", listTransactionsRequest.pan);
        Request request = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(listTransactionsRequest))
                .build();

        return Requests.sendRequest(request, CardTransactions.class);
    }

    /**
     * Calling the card transfer service that return information of transfer.
     *
     * @param request Encapsulates a card transfer request parameters such as the card credentials and destination info.
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Information of transfer.
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException When one of the required parameters were {@code null}
     */
    public static CardTransfer transfer(CardTransferRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be null");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "cards/transfer";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, CardTransfer.class);
    }
}