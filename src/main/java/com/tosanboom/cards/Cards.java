package com.tosanboom.cards;

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
     * Calling CardHolder service that return the owner name of card
     *
     * @param request Encapsulated credential's information of card
     * @param boomApi  Encapsulated header's parameters that must be send to request service, and default address url.
     * @return The response of calling service {@link CardHolder}
     */
    public static CardHolder getHolder(CardHolderRequest request, BoomApi boomApi) {
        String url = boomApi.baseUrl() + "cards/" + request.pan + "/holder";
        Request.Builder builder = new Request.Builder();
        Request httpRequest = Requests.withCommonHeaders(builder, boomApi)
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
     */
    public static CardList getCards(CardListRequest request, BoomApi boomApi) {
        String url = boomApi.baseUrl() + "cards/";
        Request.Builder builder = new Request.Builder();
        Request httpRequest = Requests.withCommonHeaders(builder, boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, CardList.class);
    }
}