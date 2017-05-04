package ir.boommarket.deposits;

import ir.boommarket.Asserts;

import java.util.Date;

/**
 * Encapsulates filtering options available to filter the list of statements
 * <p>
 * <p>If you don't want to filter the returned statement list, just create a {@linkplain StatementListRequest} with
 * an empty filtering schema:
 * <pre>
 *     StatementListRequest statementListRequest = StatementListRequest.withoutFilter()
 * </pre>
 * Otherwise, you can chain multiple parameters using the provided fluent interface:
 * <pre>
 *     StatementListRequest request = StatementListRequest.newBuilder()
 *                                                        .withDepositNumber(depositNumber)
 *                                                        .withFromDate(fromDate)
 *                                                        .withToDate(toDate)
 *                                                        .withOffset(offset)
 *                                                        .withLength(length)
 *                                                        .withOrder(order)
 *                                                        .withDescription(null)
 *                                                        .withAction(action)
 *                                                        .build()
 *
 * </pre>
 *
 * @author Marjan Mehranfar
 */
public class StatementListRequest {
    final String depositNumber;
    final Date fromDate;
    final Date toDate;
    final Long offset;
    final Long length;
    final OrderType order;
    final String description;
    final StatementActionType action;

    private StatementListRequest(String depositNumber, Date fromDate, Date toDate, Long offset,
                                Long length, OrderType order, String description, StatementActionType action) {

        Asserts.notBlank(depositNumber, "depositNumber can't be a null or blank string");
        offsetNotNegative(offset);
        lengthNotNegative(length);
        validateTimeSpan(fromDate, toDate);

        this.depositNumber = depositNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.offset = offset;
        this.length = length;
        this.order = order;
        this.description = description;
        this.action = action;
    }

    /**
     * If user want to get list of deposits without filtering can call this method
     *
     * @return An instance of {@linkplain StatementListRequest}
     */
    public static StatementListRequest withoutFilter() {
        return new Builder().build();
    }

    /**
     * If user want to get list without filtering by calling {@link #newBuilder()}
     *
     * @return A builder for {@linkplain StatementListRequest}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String depositNumber;
        private Date fromDate;
        private Date toDate;
        private Long offset;
        private Long length;
        private OrderType order;
        private String description;
        private StatementActionType action;

        /**
         * (Mandatory) The deposit number for the account to list its statements
         *
         * @param depositNumber The deposit number
         */
        public Builder withDepositNumber(String depositNumber) {
            this.depositNumber = depositNumber;
            return this;
        }

        /**
         * (Optional) Sets the starting point to show statements after that. If this value and
         * {@linkplain #toDate} were both given, <strong>{@linkplain #fromDate} shouldn't be after than
         * {@linkplain #toDate}.</strong>
         *
         * @param fromDate The from date
         */
        public Builder withFromDate(Date fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        /**
         * (Optional) Sets the endpoint for statements filtering. If this value and {@linkplain #fromDate} were both given,
         * <strong>{@linkplain #fromDate} shouldn't be after than {@linkplain #toDate}.</strong>
         *
         * @param toDate The to date
         */
        public Builder withToDate(Date toDate) {
            this.toDate = toDate;
            return this;
        }

        /**
         * (Optional) Determines the number of elements to skip before the first element to return. If given,
         * the value should be greater than or equal to zero.
         *
         * @param offset The offset from the beginning
         */
        public Builder withOffset(Long offset) {
            this.offset = offset;
            return this;
        }

        /**
         * (Optional) Determines each page size. If given, this value should be greater than zero.
         *
         * @param length The length of page
         */
        public Builder withLength(Long length) {
            this.length = length;
            return this;
        }

        /**
         * (Optional) Determines the order of returned statements. You can order statements either
         * ascending or descending based on this parameter.
         *
         * @param order The order type
         */
        public Builder withOrder(OrderType order) {
            this.order = order;
            return this;
        }

        /**
         * (Optional) Filtering statements based on {@code description}
         *
         * @param description The description
         */
        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * (Optional) Filtering statements based on the type of action that statement represents,
         *                 It can be {@linkplain StatementActionType#CREDIT} or {@linkplain StatementActionType#DEBIT}
         *
         * @param action The type of action
         */
        public Builder withAction(StatementActionType action) {
            this.action = action;
            return this;
        }

        /**
         * Building an instance of {@linkplain StatementListRequest}
         *
         * @throws IllegalArgumentException If one of the given parameters weren't valid
         */
        public StatementListRequest build() {
            return new StatementListRequest(depositNumber, fromDate, toDate, offset, length, order, description, action);
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
        if (offset != null && offset < 0)
            throw new IllegalArgumentException("offset can't be a negative number");
    }

    /**
     * If {@code length} has not null value, This method check it not be a non-positive number,
     * Otherwise it throws an {@linkplain IllegalArgumentException} with an appropriate message
     *
     * @param length The page size or length
     * @throws IllegalArgumentException If the given length was less than or equal to zero
     */
    private void lengthNotNegative(Long length) {
        if (length != null && length <= 0)
            throw new IllegalArgumentException("length can't be less than or equal to zero");
    }

    private void validateTimeSpan(Date fromDate, Date toDate) {
        if(fromDate != null && toDate != null && fromDate.after(toDate))
            throw new IllegalArgumentException("toDate should be after the fromDate");
    }
}