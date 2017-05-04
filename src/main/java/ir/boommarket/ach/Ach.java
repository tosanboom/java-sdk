package ir.boommarket.ach;

import ir.boommarket.Asserts;
import ir.boommarket.*;
import ir.boommarket.Json;
import ir.boommarket.Requests;
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
     * @throws RestApiException       When a 4xx/5xx error returns from REST API
     * @throws FailedRequestException When we couldn't send the request for whatever reason
     * @throws JsonException          When something went wrong during JSON serialization/de-serialization
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
     * @throws RestApiException       When a 4xx/5xx error returns from REST API
     * @throws FailedRequestException When we couldn't send the request for whatever reason
     * @throws JsonException          When something went wrong during JSON serialization/de-serialization
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
     * @throws RestApiException       When a 4xx/5xx error returns from REST API
     * @throws FailedRequestException When we couldn't send the request for whatever reason
     * @throws JsonException          When something went wrong during JSON serialization/de-serialization
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
     * @throws RestApiException       When a 4xx/5xx error returns from REST API
     * @throws FailedRequestException When we couldn't send the request for whatever reason
     * @throws JsonException          When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException             When one of the required parameters were {@code null}
     */
    public static AchTransfersReport transferReports(AchTransferReportRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be a null value");
        Asserts.notNull(boomApi, "boomApi can't be a null value");

        String url = boomApi.baseUrl() + "ach/reports/transfer";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, AchTransfersReport.class);
    }

    /**
     * Calling the Ach transaction report service that return report of transaction.
     *
     * @param request Encapsulates a ach transaction report request parameters
     * @param boomApi Encapsulates common headers to be sent over the wire to the boom API.
     * @return Report of transaction
     * @throws RestApiException       When a 4xx/5xx error returns from REST API
     * @throws FailedRequestException When we couldn't send the request for whatever reason
     * @throws JsonException          When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException             When one of the required parameters were {@code null}
     */
    public static AchTransactionsReport transactionReports(AchTransactionReportRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be a null value");
        Asserts.notNull(boomApi, "boomApi can't be a null value");

        String url = boomApi.baseUrl() + "ach/reports/transaction";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, AchTransactionsReport.class);
    }
}
