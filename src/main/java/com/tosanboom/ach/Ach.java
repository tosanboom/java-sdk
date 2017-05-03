package com.tosanboom.ach;

import com.tosanboom.Asserts;
import com.tosanboom.BoomApi;
import com.tosanboom.Json;
import com.tosanboom.Requests;
import okhttp3.Request;

/**
 * @author Mona Mohamadinia
 */
public class Ach {
    /**
     * Calling the Ach normal transfer service that return information of transfer.
     *
     * @param request Encapsulates a ach normal transfer request parameters
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Information of transfer.
     * @throws com.tosanboom.RestApiException       When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException          When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException             When one of the required parameters were {@code null}
     */
    public static AchNormalTransfer normalTransfer(AchNormalTransferRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be a null value");
        Asserts.notNull(boomApi, "boomApi can't be a null value");

        String url = boomApi.baseUrl() + "ach/transfer/normal";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, AchNormalTransfer.class);
    }

    /**
     * Calling the Ach batch transfer service that return information of transfer.
     *
     * @param request Encapsulates a ach batch transfer request parameters
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Information of transfer.
     * @throws com.tosanboom.RestApiException       When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException          When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException             When one of the required parameters were {@code null}
     */
    public static AchBatchTransfer batchTransfer(AchBatchTransferRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be a null value");
        Asserts.notNull(boomApi, "boomApi can't be a null value");

        String url = boomApi.baseUrl() + "ach/transfer/batch";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, AchBatchTransfer.class);
    }

    /**
     * Calling the Ach auto transfer service that return information of transfer.
     *
     * @param request Encapsulates a ach auto transfer request parameters
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Information of transfer.
     * @throws com.tosanboom.RestApiException       When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException          When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException             When one of the required parameters were {@code null}
     */
    public static AchAutoTransfer autoTransfer(AchAutoTransferRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be a null value");
        Asserts.notNull(boomApi, "boomApi can't be a null value");

        String url = boomApi.baseUrl() + "ach/transfer/auto";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, AchAutoTransfer.class);
    }

    /**
     * Calling the Ach transfer report service that return report of transfer.
     *
     * @param request Encapsulates a ach transfer report request parameters
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Report of transfer
     * @throws com.tosanboom.RestApiException       When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException          When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException             When one of the required parameters were {@code null}
     */
    public static AchTransfers transferReports(AchTransferReportRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be a null value");
        Asserts.notNull(boomApi, "boomApi can't be a null value");

        String url = boomApi.baseUrl() + "ach/reports/transfer";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, AchTransfers.class);
    }
}
