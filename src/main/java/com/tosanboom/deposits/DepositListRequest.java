package com.tosanboom.deposits;

import java.util.List;

/**
 * Encapsulates filtering options available to filter the list of deposits
 *
 * <p>If you don't want to filter the returned deposit list, just create a {@linkplain DepositListRequest} with
 * an empty filtering schema:
 * <pre>
 *     DepositListRequest depositListRequest = DepositListRequest.withoutFilter()
 * </pre>
 * Otherwise, you can chain multiple parameters using the provided fluent interface:
 * <pre>
 *     DepositListRequest request = DepositListRequest.newBuilder()
 *                                                    .withDepositNumbers(depositNumbers)
 *                                                    .withDepositStatus(depositStatus)
 *                                                    .withIncludeCreditAccount(creditAccounts)
 *                                                    .withIncludeSupportAccount(supportAccounts)
 *                                                    .withOffset(offset)
 *                                                    .withLength(length)
 *                                                    .build()
 * </pre>
 *
 * @author Marjan Mehranfar
 */
public class DepositListRequest {
    final List<String> depositNumbers;
    final DepositStatus depositStatus;
    final Boolean includeSupportAccount;
    final Boolean includeCreditAccount;
    final Long offset;
    final Long length;

    private DepositListRequest(List<String> depositNumbers, DepositStatus depositStatus, Boolean includeSupportAccount,
                              Boolean includeCreditAccount, Long offset, Long length) {
        offsetNotNegative(offset);
        lengthNotNegative(length);

        this.depositNumbers = depositNumbers;
        this.depositStatus = depositStatus;
        this.includeSupportAccount = includeSupportAccount;
        this.includeCreditAccount = includeCreditAccount;
        this.offset = offset;
        this.length = length;
    }

    /**
     * Since that the user can receive list of deposits without sending any parameters to filtering it,
     * In this method build an object of class
     *
     * @return An instance of {@link DepositListRequest} class
     */
    public static DepositListRequest withoutFilter() { return new Builder().build(); }

    /**
     * If the user want to get list of deposits without filtering and call newBuilder method, can use it
     *
     * @return An instance of {@link DepositListRequest} class
     */
    public static Builder newBuilder() { return new Builder(); }

    /**
     * A builder for {@link DepositListRequest} class
     */
    public static class Builder {
        private List<String> depositNumbers;
        private DepositStatus depositStatus;
        private Boolean includeSupportAccount;
        private Boolean includeCreditAccount;
        private Long offset;
        private Long length;

        public Builder withDepositNumbers(List<String> depositNumbers) {
            this.depositNumbers = depositNumbers;
            return this;
        }

        public Builder withDepositStatus(DepositStatus depositStatus) {
            this.depositStatus = depositStatus;
            return this;
        }

        public Builder withIncludeSupportAccount(Boolean includeSupportAccount) {
            this.includeSupportAccount = includeSupportAccount;
            return this;
        }

        public Builder withIncludeCreditAccount(Boolean includeCreditAccount) {
            this.includeCreditAccount = includeCreditAccount;
            return this;
        }

        public Builder withOffset(Long offset) {
            this.offset = offset;
            return this;
        }

        public Builder withLength(Long length) {
            this.length = length;
            return this;
        }

        /**
         * @throws IllegalArgumentException If one of the given parameters weren't valid
         */
        public DepositListRequest build() {
              return new DepositListRequest(depositNumbers, depositStatus, includeSupportAccount,
                      includeCreditAccount, offset, length);
        }
    }

    /**
     * If {@code offset} was not null, This method check it not be a negative number,
     * Otherwise it throws an {@linkplain IllegalArgumentException} with an appropriate message
     *
     * @param offset How many items to skip before the first element of the current page (offset)
     * @throws IllegalArgumentException If the given offset was a negative number
     */
    private void offsetNotNegative(Long offset) {
        if(offset != null && offset < 0)
            throw new IllegalArgumentException("Offset can't be a negative number");
    }

    /**
     * If {@code length} has not null value, This method check it not be a non-positive number,
     * Otherwise it throws an {@linkplain IllegalArgumentException} with an appropriate message
     *
     * @param length The page size or length
     * @throws IllegalArgumentException If the given length was less than or equal to zero
     */
    private void lengthNotNegative(Long length) {
        if(length != null && length <= 0)
            throw new IllegalArgumentException("Length can't be less than or equal to zero");
    }
}