package ir.boommarket.banks;

import ir.boommarket.Asserts;
import ir.boommarket.BoomApi;
import ir.boommarket.Requests;
import okhttp3.Request;

/**
 * Entry point for all bank related services
 *
 * @author Ali Dehghani
 */
public class Banks {
    /**
     * Get a list of branches
     *
     * @param request Encapsulates information for branch request such as the bank and pagination
     *                information
     * @param boomApi Encapsulates contextual information about the boom API
     * @return The branch list
     */
    public static BranchList getBranches(BranchListRequest request, BoomApi boomApi) {
        Asserts.notNull(request, "request can't be null");
        Asserts.notNull(boomApi, "boomApi can't be null");

        String url = boomApi.baseUrl() + "banks/" + request.bank.swiftCode() + "/branches" + request.paginationQueryParam();
        Request httpRequest = new Request.Builder().url(url).build();

        return Requests.sendRequest(httpRequest, BranchList.class);
    }
}