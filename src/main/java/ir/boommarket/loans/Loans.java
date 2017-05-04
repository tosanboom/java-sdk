package ir.boommarket.loans;

import ir.boommarket.Asserts;
import ir.boommarket.*;
import ir.boommarket.Requests;
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
     * @throws RestApiException If REST API return a 4xx/5xx error
     * @throws FailedRequestException When we couldn't manage to send the request
     * @throws JsonException If we couldn't read/write the JSON to/from a pojo
     * @throws IllegalArgumentException If either of required parameters were {@code null}
     */
    public static LoanDetails getDetails(LoanDetailsRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be null");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "loans/" + request.loanNumber + request.toQueryParam();
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi).url(url).get().build();

        return Requests.sendRequest(httpRequest, LoanDetails.class);
    }

    /**
     * Retrieves owner information for the specified loan
     *
     * @param loanNumber The loan number to see its loan owner information
     * @param boomApi Encapsulates contextual information about the boom API
     * @return An instance of {@link LoanOwner} representing the details of the given loan owner
     * @throws RestApiException If REST API return a 4xx/5xx error
     * @throws FailedRequestException When we couldn't manage to send the request
     * @throws JsonException If we couldn't read/write the JSON to/from a pojo
     * @throws IllegalArgumentException If either of required parameters were {@code null} or invalid
     */
    public static LoanOwner getLoanOwner(String loanNumber, BoomApi boomApi) {
        Asserts.notBlank(loanNumber, "loanNumber can't be blank");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "loans/owner?loan_number=" + loanNumber;
        Request httpRequest = Requests.withCommonHeaders(new Request.Builder(), boomApi).url(url).get().build();

        return Requests.sendRequest(httpRequest, LoanOwner.class);
    }
}