package com.tosanboom.deposits;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    final String fromDate;
    final String toDate;
    final Long offset;
    final Long length;
    final OrderType order;
    final String description;
    final StatementActionType action;

    public StatementListRequest(String depositNumber, String fromDate, String toDate, Long offset,
                                Long length, OrderType order, String description, StatementActionType action) {
        /**
         * Check validation of some parameters
         */
        depositNotEmptyOrNull(depositNumber);
        offsetNotNegative(offset);
        lengthNotNegative(length);

        if (fromDate != null)
            dateIsValidFormat(fromDate);

        if (toDate != null)
            dateIsValidFormat(toDate);

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
        private String fromDate;
        private String toDate;
        private Long offset;
        private Long length;
        private OrderType order;
        private String description;
        private StatementActionType action;

        public Builder withDepositNumber(String depositNumber) {
            this.depositNumber = depositNumber;
            return this;
        }

        public Builder withFromDate(String fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder withToDate(String toDate) {
            this.toDate = toDate;
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

        public Builder withOrder(OrderType order) {
            this.order = order;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withAction(StatementActionType action) {
            this.action = action;
            return this;
        }

        /**
         * @throws IllegalArgumentException If one of the given parameters weren't valid
         */
        public StatementListRequest build() {
            return new StatementListRequest(depositNumber, fromDate, toDate, offset, length, order, description, action);
        }
    }

    private void depositNotEmptyOrNull(String depositNumber) {
        if (depositNumber == null || depositNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("DepositNumber can't be a null or blank string");
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
        if (length != null && length <= 0)
            throw new IllegalArgumentException("Length can't be less than or equal to zero");
    }

    private boolean dateIsValidFormat(String value) {
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Format of date is not true, It must be in yyyy-MM-dd'T'HH:mm:ss'Z' format");
        }
        return date != null;
    }
}
