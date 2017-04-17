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
}