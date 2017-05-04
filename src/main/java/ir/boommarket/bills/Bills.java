package ir.boommarket.bills;

import ir.boommarket.Asserts;
import ir.boommarket.BoomApi;
import ir.boommarket.Json;
import ir.boommarket.Requests;
import ir.boommarket.*;
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
     * Calling the get Bill info service that return bill information.
     *
     * @param request Encapsulate mandatory parameters that must be set to get bill information
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Bill information
     * @throws RestApiException When a 4xx/5xx error returns from REST API
     * @throws FailedRequestException When we couldn't send the request for whatever reason
     * @throws JsonException When something went wrong during JSON serialization/de-serialization
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

    /**
     * Paying a bill using the specified deposit
     *
     * @param request Encapsulate parameters that must be set to pay bill.
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Information of paid bill.
     * @throws RestApiException When a 4xx/5xx error returns from REST API
     * @throws FailedRequestException When we couldn't send the request for whatever reason
     * @throws JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException When one of the required parameters were {@code null}
     */
    public static BillPayment payBillByDeposit(BillPaymentByDepositRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be null");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "bills/" + request.billId + "/payments/" + request.payId + "/deposit";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, BillPayment.class);
    }

    /**
     * Paying a bill using the specified card
     *
     * @param request Encapsulate parameters that must be set to pay bill.
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Information of paid bill.
     * @throws RestApiException When a 4xx/5xx error returns from REST API
     * @throws FailedRequestException When we couldn't send the request for whatever reason
     * @throws JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException When one of the required parameters were {@code null}
     */
    public static BillPayment payBillByCard(BillPaymentByCardRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be null");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "bills/" + request.billId + "/payments/" + request.payId + "/card";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                                      .url(url)
                                      .post(Json.of(request))
                                      .build();

        return Requests.sendRequest(httpRequest, BillPayment.class);
    }
}