package com.tosanboom.bills;

import com.tosanboom.BoomApi;
import com.tosanboom.Json;
import com.tosanboom.Requests;
import okhttp3.Request;

/**
 * Entry point for calling all bill related services. Usually each method's first
 * argument is specific to the service itself and the second argument is an instance of
 * {@linkplain BoomApi}.
 *
 * @author Mona Mohamadinia
 */
public class Bills {
    /**
     * Paying a bill using the specified card
     *
     * @param request Encapsulate parameters that must be set to pay bill.
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Information of paid bill.
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException When one of the required parameters were {@code null}
     */
    public static BillPayment payBillByCard(BillPaymentByCardRequest request, BoomApi boomApi) {
        String url = boomApi.baseUrl() + "bills/" + request.billId + "/payments/" + request.payId + "/card";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, BillPayment.class);
    }
}