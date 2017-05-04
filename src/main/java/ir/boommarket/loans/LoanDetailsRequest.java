package ir.boommarket.loans;

import ir.boommarket.Asserts;
import ir.boommarket.QueryParams;

/**
 * Encapsulates filtering options available to filter a specific loan details request
 *
 * <h1>How to Use?</h1>
 * In order to build an instance of {@linkplain LoanDetailsRequest}, you should at least pass
 * the only mandatory parameter which is {@linkplain #loanNumber}:
 * <pre>
 *     LoanDetailsRequest request = LoanDetailsRequest.forLoanNumber(loanNumber).build();
 * </pre>
 * Also you can chain multiple filtering options together before bridling the {@linkplain LoanDetailsRequest}:
 * <pre>
 *     LoanDetailsRequest request = LoanDetailsRequest.forLoanNumber(LOAN_NUMBER)
 *                                                    .showDetails()
 *                                                    .withLength(length)
 *                                                    .withOffset(offset)
 *                                                    .withPayStatus(status)
 *                                                    .build()
 * </pre>
 *
 * @author Ali Dehghani
 */
public class LoanDetailsRequest {
    final String loanNumber;
    final boolean hasDetail;
    final PayStatus payStatus;
    final Long length;
    final Long offset;

    String toQueryParam() {
        return QueryParams.newQuery()
                .with("has_detail", hasDetail)
                .with("pay_status", payStatus)
                .with("length", length)
                .with("offset", offset)
                .toString();
    }

    private LoanDetailsRequest(String loanNumber, boolean hasDetail, PayStatus payStatus, Long length, Long offset) {
        Asserts.notBlank(loanNumber, "Loan number can't be null");
        validatePaginationParameters(length, offset);

        this.loanNumber = loanNumber;
        this.hasDetail = hasDetail;
        this.payStatus = payStatus;
        this.length = length;
        this.offset = offset;
    }

    /**
     * Static factory method that acts as a nexus to the fluent {@link Builder}
     *
     * @param loanNumber The loan number to see its details
     * @return An instance of {@link Builder}
     */
    public static Builder forLoanNumber(String loanNumber) {
        return new Builder().withLoanNumber(loanNumber);
    }

    public static class Builder {
        private String loanNumber;
        private boolean hasDetail = false;
        private PayStatus payStatus;
        private Long length;
        private Long offset;

        /**
         * (Mandatory) Sets the loan number. This parameter is required and will be validated
         *
         * @param loanNumber The loan number to see its details
         * @return The builder itself
         */
        public Builder withLoanNumber(String loanNumber) {
            this.loanNumber = loanNumber;
            return this;
        }

        /**
         * Instruct the loan details service to show payments made so far, for the given loan number.
         *
         * @return The builder itself
         * @see LoanDetails#loanRows
         */
        public Builder showDetails() {
            hasDetail = true;
            return this;
        }

        /**
         * Filter loan payments that are only in the specified state
         *
         * @param payStatus The payment state to filter
         * @return The builder itself
         * @see LoanDetails#loanRows
         */
        public Builder withPayStatus(PayStatus payStatus) {
            this.payStatus = payStatus;
            return this;
        }

        /**
         * Determines each page size for loan rows. If given, it should be greater than zero.
         *
         * @param length Page size
         * @return The builder itself
         * @see LoanDetails#loanRows
         */
        public Builder withLength(Long length) {
            this.length = length;
            return this;
        }

        /**
         * Determines the offset. If given, it should be greater than or equal to zero.
         *
         * @param offset The number of elements to skip in loan rows
         * @return The builder itself
         * @see LoanDetails#loanRows
         */
        public Builder withOffset(Long offset) {
            this.offset = offset;
            return this;
        }

        /**
         * Build an instance of {@linkplain LoanDetailsRequest} from the accumulated state of the builder
         */
        public LoanDetailsRequest build() {
            return new LoanDetailsRequest(loanNumber, hasDetail, payStatus, length, offset);
        }
    }

    private void validatePaginationParameters(Long length, Long offset) {
        if (length != null && length <= 0)
            throw new IllegalArgumentException("Length can't be less than or equal to zero");

        if (offset != null && offset < 0)
            throw new IllegalArgumentException("Offset can't be less than zero");
    }
}