package com.tosanboom.deposits;

import com.tosanboom.Asserts;
import com.tosanboom.BoomApi;
import com.tosanboom.Json;
import com.tosanboom.Requests;
import okhttp3.Request;

/**
 * Entry point for calling all deposit related services. Usually each method's first
 * argument is specific to the service itself and the second argument is an instance of
 * {@linkplain BoomApi}.
 *
 * @author Marjan Mehranfar
 */
public class Deposits {
    /**
     * Get details information of a given {@code depositNumber}
     *
     * @param depositNumber The deposit number
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return An instance of {@linkplain Deposit} which represents the information of the given {@code depositNumber}
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     */
    public static Deposit getDetail(String depositNumber, BoomApi boomApi) {
        if (depositNumber == null || depositNumber.trim().isEmpty())
            throw new IllegalArgumentException("DepositNumber must not be null or a blank string");

        String url = boomApi.baseUrl() + "deposits/" + depositNumber;
        Request.Builder builder = new Request.Builder();
        Request httpRequest = Requests.withCommonHeaders(builder, boomApi)
                .url(url)
                .get()
                .build();

        return Requests.sendRequest(httpRequest, Deposit.class);
    }

    /**
     * Return owner name of the given {@code depositNumber}
     *
     * @param depositNumber The number of deposit
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return An instance of {@linkplain DepositHolder} that represents owner name of the given {@code depositNumber}
     */
    public static DepositHolder getHolder(String depositNumber, BoomApi boomApi) {
        if (depositNumber == null || depositNumber.trim().isEmpty())
            throw new IllegalArgumentException("DepositNumber must not be null or a blank string");

        String url = boomApi.baseUrl() + "deposits/" + depositNumber + "/holder";
        Request.Builder builder = new Request.Builder();
        Request httpRequest = Requests.withCommonHeaders(builder, boomApi)
                .url(url)
                .get()
                .build();

        return Requests.sendRequest(httpRequest, DepositHolder.class);
    }

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
        Asserts.notNull(request, "The request parameter can not be null");
        Asserts.notNull(boomApi, "The boomApi parameter can not be null");

        String url = boomApi.baseUrl() + "deposits/" + request.depositNumber + "/statements";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, StatementList.class);
    }

    /**
     * Receive list of deposits for current authenticated user
     *
     * @param request {@linkplain DepositListRequest} encapsulates parameters to filter the deposit list
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return An instance of {@linkplain DepositList} , It has list of {@linkplain Deposit}
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException If request parameters were null
     */
    public static DepositList getDeposits(DepositListRequest request, BoomApi boomApi) {
        if (request == null) request = DepositListRequest.withoutFilter();
        Asserts.notNull(boomApi, "BoomApi can not be null");

        String url = boomApi.baseUrl() + "deposits";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, DepositList.class);
    }

    /**
     * Return IBAN number of the given {@code depositNumber}
     *
     * @param depositNumber The deposit number
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return The corresponding IBAN number
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException If the given parameters were null or a blank string
     */
    public static DepositIban getIban(String depositNumber, BoomApi boomApi) {
        Asserts.notBlank(depositNumber, "DepositNumber must not be null or a blank string");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "deposits/" + depositNumber + "/iban";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .get()
                .build();

        return Requests.sendRequest(httpRequest, DepositIban.class);
    }

    /**
     * Transfer given amount of money between two deposits in the same bank, i.e. Deposit Normal Transfer
     *
     * @param request Encapsulates required and some optional information to transfer between two deposits
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return Represents state of a successful normal transfer
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException If the given parameters were null
     */
    public static NormalTransfer normalTransfer(NormalTransferRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request parameter can not be null");
        Asserts.notNull(boomApi, "boomApi parameter can not be null");

        String url = boomApi.baseUrl() + "deposits/transfer/normal";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, NormalTransfer.class);
    }

    /**
     * Transfer a specified amount between a source and a destination periodically.
     *
     * @param request Encapsulates the information of the transfer
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return A trackingNumber to trace the auto transfer
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException If the given parameters were null
     */
    public static AutoTransfer autoTransfer(AutoTransferRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "The request parameter can not be null");
        Asserts.notNull(boomApi, "The boomApi can not be null");

        String url = boomApi.baseUrl() + "deposits/transfer/auto";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, AutoTransfer.class);
    }

    /**
     * Disables auto transfer for the given {@code serial}
     *
     * @param serial The identifier (serial) for a previously submitted periodic (auto) transfer
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return An instance of {@linkplain CancelAutoTransfer}, It has the number of transactions that disabled
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException If the given parameters were null or a blank string
     */
    public static CancelAutoTransfer cancelAutoTransfer(String serial, BoomApi boomApi) {
        Asserts.notBlank(serial, "Serial parameter can not be null or a blank string");
        Asserts.notNull(boomApi, "BoomApi can't be null");

        String url = boomApi.baseUrl() + "deposits/transfer/auto/" + serial;
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .delete()
                .build();

        return Requests.sendRequest(httpRequest, CancelAutoTransfer.class);
    }

    /**
     * Get reports of auto transfer
     *
     * @param request Encapsulates some parameters to filtering returned list
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return List of reports from auto transfer
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException When one of parameters were null
     */
    public static AutoTransferReport getAutoTransferReports(ReportAutoTransferRequest request, BoomApi boomApi) {
        if (request == null) request = ReportAutoTransferRequest.withoutFilter();

        Asserts.notNull(boomApi, "BoomApi can't be null");

        String url = boomApi.baseUrl() + "deposits/transfer/auto/reports";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, AutoTransferReport.class);
    }

    /**
     * Get list of auto transfer that the user has been requested
     *
     * @param request Encapsulates some parameters to filtering received list of (auto) transfers
     * @param boomApi Encapsulates the contextual information about the boom api
     * @return List of information from (auto) transfers
     * @throws com.tosanboom.RestApiException When a 4xx/5xx error returns from REST API
     * @throws com.tosanboom.FailedRequestException When we couldn't send the request for whatever reason
     * @throws com.tosanboom.JsonException When something went wrong during JSON serialization/de-serialization
     * @throws IllegalArgumentException When {@code boomApi} was null
     */
    public static ListAutoTransfer getListAutoTransfer(ListAutoTransferRequest request, BoomApi boomApi) {
        if (request == null) request = ListAutoTransferRequest.withoutFilter();

        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "deposits/transfer/auto/list";
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi)
                .url(url)
                .post(Json.of(request))
                .build();

        return Requests.sendRequest(httpRequest, ListAutoTransfer.class);
    }
}