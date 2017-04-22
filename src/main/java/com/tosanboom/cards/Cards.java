package com.tosanboom.cards;

import com.tosanboom.BoomApi;
import com.tosanboom.Json;
import com.tosanboom.Requests;
import okhttp3.Request;

public class Cards {
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