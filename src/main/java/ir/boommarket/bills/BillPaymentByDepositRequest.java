package ir.boommarket.bills;

import ir.boommarket.Asserts;

/**
 * Encapsulates mandatory/optional parameters that must/can be used to
 * pay bill by deposit
 *
 * @author Mona Mohamadinia
 */
public class BillPaymentByDepositRequest {
    /**
     * (Mandatory) The bill identifier
     */
    final String billId;

    /**
     * (Mandatory) The payment identifier
     */
    final String payId;

    /**
     * (Mandatory) The Deposit number
     */
    final String depositNumber;

    /**
     * (Optional) The customer number
     */
    final String customerNumber;

    /**
     * (Optional) The bill payment needs verification
     */
    final boolean requireVerification;

    /**
     * (Optional) The time out for verification process.
     */
    final Long verificationExpirationTimeOut;

    private BillPaymentByDepositRequest(String billId, String payId, String customerNumber, String depositNumber,
                                       boolean requireVerification, Long verificationExpirationTimeOut) {

        assertRequiredParams(billId, payId, depositNumber);
        verificationExpirationTimeOutNotNegative(verificationExpirationTimeOut);

        this.billId = billId;
        this.payId = payId;
        this.depositNumber = depositNumber;
        this.customerNumber = customerNumber;
        this.requireVerification = requireVerification;
        this.verificationExpirationTimeOut = verificationExpirationTimeOut;
    }

    private void assertRequiredParams(String billId, String payId, String depositNumber) {
        Asserts.notBlank(billId, "Bill Id can't be a blank string");
        Asserts.notBlank(payId, "Pay Id can't be a blank string");
        Asserts.notBlank(depositNumber, "Deposit number can't be a blank string");
    }

    private void verificationExpirationTimeOutNotNegative(Long verificationExpirationTimeOut) {
        if (verificationExpirationTimeOut!= null && verificationExpirationTimeOut <= 0)
            throw new IllegalArgumentException("Verification time can't be negative");
    }

    /**
     * A static factory method which is the nexus to the {@linkplain BillPaymentByDepositRequest}'s {@linkplain Builder}.
     *
     * @return The fluent {@linkplain Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * A fluent builder for {@linkplain BillPaymentByDepositRequest}. You can chain different building operations together and
     * whenever you feel this is the time, build an instance of {@linkplain BillPaymentByDepositRequest} using the
     * notorious {@linkplain #build()} method.
     */
    public static class Builder {
        private String billId;
        private String payId;
        private String customerNumber;
        private String depositNumber;
        private boolean requireVerification;
        private Long verificationExpirationTimeOut;

        /**
         * Set the bill identifier
         *
         * @param billId The bill identifier
         * @return The builder itself
         */
        public Builder withBillId(String billId) {
            this.billId = billId;
            return this;
        }

        /**
         * Set the payment identifier
         *
         * @param payId The payment identifier
         * @return The builder itself
         */
        public Builder withPayId(String payId){
            this.payId = payId;
            return this;
        }

        /**
         * Set the customer number
         *
         * @param customerNumber The customer number
         * @return The builder itself
         */
        public Builder withCustomerNumber(String customerNumber){
            this.customerNumber = customerNumber;
            return this;
        }

        /**
         * Set the deposit number
         *
         * @param depositNumber The deposit number
         * @return The builder itself
         */
        public Builder withDepositNumber(String depositNumber){
            this.depositNumber = depositNumber;
            return this;
        }

        /**
         * Set true if payment needs verification.
         *
         * @param requireVerification The bill payment needs verification
         * @return The builder itself
         */
        public Builder withRequireVerification(boolean requireVerification){
            this.requireVerification = requireVerification;
            return this;
        }

        /**
         * Set the time out for verification process.
         *
         * @param verificationExpirationTimeOut The time out for verification process.
         * @return The builder itself
         */
        public Builder withVerificationExpirationTimeOut(Long verificationExpirationTimeOut){
            this.verificationExpirationTimeOut = verificationExpirationTimeOut;
            return this;
        }

        /**
         * Build the {@linkplain BillPaymentByDepositRequest} out of the current builder.
         *
         * @return An instance of {@linkplain BillPaymentByDepositRequest}
         * @throws IllegalArgumentException If one of the parameters were missing or invalid
         */
        public BillPaymentByDepositRequest build() {
            return new BillPaymentByDepositRequest(billId, payId, customerNumber, depositNumber,
                                                   requireVerification, verificationExpirationTimeOut);
        }
    }
}
