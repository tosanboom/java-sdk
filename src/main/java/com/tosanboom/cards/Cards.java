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
     * Calling the card transfer service that return information of transfer.
     *
     * @param request Encapsulates a card transfer request parameters such as the card credentials and destination info.
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Information of transfer.
     */
    public static CardTransfer transfer(CardTransferRequest request, BoomApi boomApi) {
        String url = boomApi.baseUrl() + "cards/transfer";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, CardTransfer.class);
    }
}