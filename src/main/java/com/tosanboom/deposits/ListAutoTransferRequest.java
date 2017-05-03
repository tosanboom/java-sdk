package com.tosanboom.deposits;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Encapsulates filtering options available to filter the list of auto transfer
 *
 * <p>If you don't want to filter the returned list, just create a {@linkplain ListAutoTransferRequest} with
 * an empty filtering schema:
 * <pre>
 *     ListAutoTransferRequest listAutoTransferRequest = ListAutoTransferRequest.withoutFilter()
 * </pre>
 * Otherwise, you can chain multiple parameters using the provided fluent interface:
 * <pre>
 *                      ListAutoTransferRequest.newBuilder()
 *                                             .withSourceDepositNumber(sourceDeposit)
 *                                             .withOffset(offset)
 *                                             .withLength(length)
 *                                             .withMinAmount(minAmount)
 *                                             .withMaxAmount(maxAmount)
 *                                             .withSerial(serial)
 *                                             .withStartDate(startDate)
 *                                             .withEndDate(endDate)
 *                                             .build()
 * </pre>
 *
 * @author Marjan Mehranfar
 */
public class ListAutoTransferRequest {
    final String sourceDepositNumber;
    final String serial;
    final Date startDate;
    final Date endDate;
    final BigDecimal minAmount;
    final BigDecimal maxAmount;
    final Long offset;
    final Long length;

    private ListAutoTransferRequest(String sourceDepositNumber, String serial, Date startDate, Date endDate,
                                    BigDecimal minAmount, BigDecimal maxAmount, Long offset, Long length) {

        notZeroOrNegativeAmount(minAmount);
        notZeroOrNegativeAmount(maxAmount);
        notZeroOrNegative(length);
        notNegative(offset);

        this.sourceDepositNumber = sourceDepositNumber;
        this.serial = serial;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.offset = offset;
        this.length = length;
    }

    /**
     * Method to create a filter-less request
     *
     * @return An instance of {@linkplain ListAutoTransferRequest}
     */
    public static ListAutoTransferRequest withoutFilter() {return new Builder().build(); }

    /**
     * @return An instance of {@linkplain Builder}
     */
    public static Builder newBuilder() {return new Builder(); }

    public static class Builder {
        private String sourceDepositNumber;
        private String serial;
        private Date startDate;
        private Date endDate;
        private BigDecimal minAmount;
        private BigDecimal maxAmount;
        private Long offset;
        private Long length;

        /**
         * The source deposit number of auto transfer
         *
         * @param sourceDepositNumber The deposit number of source
         */
        public Builder withSourceDepositNumber(String sourceDepositNumber) {
            this.sourceDepositNumber = sourceDepositNumber;
            return this;
        }

        /**
         * Get list of an auto transfer with the given {@code serial}
         *
         * @param serial The serial of an auto transfer
         */
        public Builder withSerial(String serial) {
            this.serial = serial;
            return this;
        }

        /**
         * The date to start auto transfer
         *
         * @param startDate The start date
         */
        public Builder withStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        /**
         * The date of ending filter auto transfer
         *
         * @param endDate The end date for filtering
         */
        public Builder withEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        /**
         * Determines the minimum amount of auto transfer to filter the list
         *
         * @param minAmount The minimum amount
         */
        public Builder withMinAmount(BigDecimal minAmount) {
            this.minAmount = minAmount;
            return this;
        }

        /**
         * Determines the maximum amount of auto transfer to filter the list
         *
         * @param maxAmount The maximum amount
         */
        public Builder withMaxAmount(BigDecimal maxAmount) {
            this.maxAmount = maxAmount;
            return this;
        }

        /**
         * Set the size of each page, defaults to 1. This value should be a positive number.
         *
         * @param length The length or size of each page
         */
        public Builder withLength(Long length) {
            this.length = length;
            return this;
        }

        /**
         * Set offset for pagination
         *
         * @param offset The non-negative offset value
         */
        public Builder withOffset(Long offset) {
            this.offset = offset;
            return this;
        }

        /**
         * It throws an {@link IllegalArgumentException}, If one of parameter weren't valid
         *
         * @return An instance of {@linkplain ListAutoTransferRequest}
         */
        public ListAutoTransferRequest build() {
            return new ListAutoTransferRequest(sourceDepositNumber, serial, startDate,
                    endDate, minAmount, maxAmount, offset, length); }
    }

    private void notNegative(Long offset) {
        if (offset != null && offset < 0)
            throw new IllegalArgumentException("offset can't be a negative number");
    }

    private void notZeroOrNegative(Long length) {
        if (length != null && length <= 0)
            throw new IllegalArgumentException("length can't be less than or equal to zero");
    }

    private void notZeroOrNegativeAmount(BigDecimal amount) {
        if (amount != null && amount.signum() <= 0)
            throw new IllegalArgumentException("amount can't be a negative value");
    }
}