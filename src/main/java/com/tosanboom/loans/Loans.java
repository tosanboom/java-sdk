package com.tosanboom.loans;

import com.tosanboom.Asserts;
import com.tosanboom.BoomApi;
import com.tosanboom.Requests;
import okhttp3.Request;

/**
 * Entry point for calling all loan related services. Usually each method's first
 * argument is specific to the service itself and the second argument is an instance of
 * {@linkplain BoomApi}.
 *
 * @author Ali Dehghani
 */
public class Loans {
    /**
     * Retrieves details of the specified loan
     *
     * @param request Encapsulates filtering options available to filter a specific loan details request
     * @param boomApi Encapsulates contextual information about the boom API
     * @return An instance of {@link LoanDetails} representing the details of the given loan
     * @throws com.tosanboom.RestApiException If REST API return a 4xx/5xx error
     * @throws com.tosanboom.FailedRequestException When we couldn't manage to send the request
     * @throws com.tosanboom.JsonException If we couldn't read/write the JSON to/from a pojo
     * @throws IllegalArgumentException If either of required parameters were {@code null}
     */
    public static LoanDetails getDetails(LoanDetailsRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be null");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "loans/" + request.loanNumber + request.toQueryParam();
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi).url(url).get().build();

        return Requests.sendRequest(httpRequest, LoanDetails.class);
    }
}