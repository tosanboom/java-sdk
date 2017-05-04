package ir.boommarket.bills;

/**
 * Encapsulates mandatory/optional parameters that must/can be used to
 * pay a bill with card.
 *
 * @author Mona Mohamadinia
 */
public class BillPaymentByCardRequest {
    final String pinType = "EPAY";

    /**
     * (Mandatory) Card number
     */
    final String pan;

    /**
     * (Mandatory) The card pin
     */
    final String pin;

    /**
     * (Mandatory) cvv2 for the card
     */
    final String cvv2;

    /**
     * (Mandatory) The expiration date of the card in {@code yymm} format in Hejri Shamsi, e.g. 9911.
     */
    final String expDate;

    /**
     * (Mandatory) The bill identifier
     */
    final String billId;

    /**
     * (Mandatory) The payment identifier
     */
    final String payId;

    /**
     * (Optional) The merchant number
     */
    final String merchantId;

    /**
     * (Optional) The bill payment needs verification
     */
    final boolean requireVerification;

    /**
     * (Optional) The time out for verification process.
     */
    final Long verificationExpirationTimeOut;

    private BillPaymentByCardRequest(String pan, String pin, String cvv2, String expDate, String billId, String payId,
                                     String merchantId, boolean requireVerification, Long verificationExpirationTimeOut) {
        panNotEmpty(pan);
        pinNotEmpty(pin);
        cvv2NotEmpty(cvv2);
        expDateNotEmpty(expDate);
        billIdNotEmpty(billId);
        payIdNotEmpty(payId);
        verificationExpirationTimeOutNotNegative(verificationExpirationTimeOut);

        this.pan = pan;
        this.pin = pin;
        this.cvv2 = cvv2;
        this.expDate = expDate;
        this.billId = billId;
        this.payId = payId;
        this.merchantId = merchantId;
        this.requireVerification = requireVerification;
        this.verificationExpirationTimeOut = verificationExpirationTimeOut;
    }

    private void billIdNotEmpty(String billId) {
        if (billId == null || billId.trim().isEmpty())
            throw new IllegalArgumentException("BillId can't be a blank string");
    }

    private void payIdNotEmpty(String payId) {
        if (payId == null || payId.trim().isEmpty())
            throw new IllegalArgumentException("PayId can't be a blank string");
    }

    private void expDateNotEmpty(String expDate) {
        if (expDate == null || expDate.trim().isEmpty())
            throw new IllegalArgumentException("Expire date can't be a blank string");
    }

    private void cvv2NotEmpty(String cvv2) {
        if (cvv2 == null || cvv2.trim().isEmpty())
            throw new IllegalArgumentException("Cvv2 can't be a blank string");
    }

    private void pinNotEmpty(String pin) {
        if (pin == null || pin.trim().isEmpty())
            throw new IllegalArgumentException("Pin can't be a blank string");
    }

    private void panNotEmpty(String pan) {
        if (pan == null || pan.trim().isEmpty())
            throw new IllegalArgumentException("Pan can't be a blank string");
    }

    private void verificationExpirationTimeOutNotNegative(Long verificationExpirationTimeOut){
        if (verificationExpirationTimeOut != null && verificationExpirationTimeOut <= 0)
            throw new IllegalArgumentException("Verification time can't be negative");
    }

    /**
     * A static factory method which is the nexus to the {@linkplain BillPaymentByCardRequest}'s {@linkplain Builder}.
     *
     * @return The fluent {@linkplain Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * A fluent builder for {@linkplain BillPaymentByCardRequest}. You can chain different building operations together and
     * whenever you feel this is the time, build an instance of {@linkplain BillPaymentByCardRequest} using the
     * notorious {@linkplain #build()} method.
     */
    public static class Builder {
        private String pan;
        private String pin;
        private String cvv2;
        private String expDate;
        private String billId;
        private String payId;
        private String merchantId;
        private boolean requireVerification;
        private Long verificationExpirationTimeOut;

        /**
         * Set the card or PAN number to the given {@code pan}
         *
         * @param pan The card number you want to pay the bill.
         * @return The builder itself
         */
        public Builder withPan(String pan) {
            this.pan = pan;
            return this;
        }

        /**
         * The second or internet password for the card
         *
         * @param pin The card
         * @return The builder itself
         */
        public Builder withPin(String pin) {
            this.pin = pin;
            return this;
        }

        /**
         * The CVV2 of the card
         *
         * @param cvv2 The CVV2 of the card
         * @return The builder itself
         */
        public Builder withCvv2(String cvv2) {
            this.cvv2 = cvv2;
            return this;
        }

        /**
         * The expiration date of the card with the {@code YYMM} format in Hejri Shamsi, e.g. {@code 9911}
         *
         * @param expDate The expiation date, e.g. {@code 9911}
         * @return The builder itself
         */
        public Builder withExpDate(String expDate) {
            this.expDate = expDate;
            return this;
        }

        /**
         * The bill identifier
         *
         * @param billId The bill identifier
         * @return The builder itself
         */
        public Builder withBillId(String billId) {
            this.billId = billId;
            return this;
        }

        /**
         * The payment identifier
         *
         * @param payId The payment identifier
         * @return The builder itself
         */
        public Builder withPayId(String payId) {
            this.payId = payId;
            return this;
        }

        /**
         * The merchant number
         *
         * @param merchantId The merchant number
         * @return The builder itself
         */
        public Builder withMerchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        /**
         * Set true if bill payment needs verification.
         *
         * @param requireVerification The bill payment needs verification
         * @return The builder itself
         */
        public Builder withRequireVerification(boolean requireVerification) {
            this.requireVerification = requireVerification;
            return this;
        }

        /**
         * Set the time out for verification process.
         *
         * @param verificationExpirationTimeOut The time out for verification process.
         * @return The builder itself
         */
        public Builder withVerificationExpirationTimeOut(Long verificationExpirationTimeOut) {
            this.verificationExpirationTimeOut = verificationExpirationTimeOut;
            return this;
        }

        /**
         * Build the {@linkplain BillPaymentByCardRequest} out of the current builder.
         *
         * @return An instance of {@linkplain BillPaymentByCardRequest}
         * @throws IllegalArgumentException If one of the parameters were missing or invalid
         */
        public BillPaymentByCardRequest build() {
            return new BillPaymentByCardRequest(pan, pin, cvv2, expDate, billId, payId, merchantId,
                                                requireVerification, verificationExpirationTimeOut);
        }
    }
}