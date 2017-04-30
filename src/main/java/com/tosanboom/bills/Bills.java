package com.tosanboom.bills;

import com.tosanboom.Asserts;
import com.tosanboom.BoomApi;
import com.tosanboom.Requests;
import okhttp3.Request;

/**
 * @author Mona Mohamadinia
 */
public class Bills {
    /**
     * Calling the get Bill info service that return bill information.
     *
     * @param request Encapsulate mandatory parameters that must be set to get bill information
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Bill information
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException When one of the required parameters were {@code null}
     */
    public static BillInfo getBillInfo(BillInfoRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be null");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "bills/" + request.billId + "/payments/" + request.payId;
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .get()
                .build();

        return Requests.sendRequest(httpRequest, BillInfo.class);
    }
}
