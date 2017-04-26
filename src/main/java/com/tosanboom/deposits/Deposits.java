package com.tosanboom.deposits;

import com.tosanboom.BoomApi;
import com.tosanboom.Json;
import com.tosanboom.Requests;
import okhttp3.Request;

public class Deposits {
    /**
     * List statements of a deposit
     *
     * @param request Encapsulates the filtering parameters to get statements of the given deposit number
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return List of {@linkplain com.tosanboom.deposits.StatementList.Statement} of the given
     *         deposit number
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException When some of the required parameters were null
     */
    public static StatementList getStatements(StatementListRequest request, BoomApi boomApi) {
        if (request == null)
            throw new IllegalArgumentException("The request parameter can not be null");

        if (boomApi == null)
            throw new IllegalArgumentException("The boomApi parameter can not be null");

        String url = boomApi.baseUrl() + "deposits/" + request.depositNumber + "/statements";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, StatementList.class);
    }
}