package com.tosanboom.bills;

import com.tosanboom.Asserts;

/**
 * Encapsulate mandatory parameters that must be set to get bill information
 *
 * @author Mona Mohamadinia
 */
public class BillInfoRequest {
    final String billId;
    final String payId;

    /**
     * Create an instance of {@linkplain BillInfoRequest} class to prepare a request to get bill information.
     *
     * @param billId bill identifier
     * @param payId bill payment identifier
     * @throws IllegalArgumentException If one of the required parameters were missing
     */
    public BillInfoRequest(String billId, String payId) {
        validateRequiredParameters(billId, payId);
        this.billId = billId;
        this.payId = payId;
    }

    private void validateRequiredParameters(String billId, String payId) {
        Asserts.notBlank(billId, "billId can't be a blank string");
        Asserts.notBlank(payId, "payId can't be a blank string");
    }
}
