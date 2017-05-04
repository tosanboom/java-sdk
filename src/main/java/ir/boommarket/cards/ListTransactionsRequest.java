package ir.boommarket.cards;

import ir.boommarket.Asserts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Encapsulates mandatory/optional parameters that must/can be used to
 * list latest transactions of a given card.
 *
 * <p>
 * <h1>How to build a valid {@linkplain ListTransactionsRequest}?</h1>
 * <p>In order to create a valid {@linkplain ListTransactionsRequest} instance, you should pass
 * all of the mandatory parameters which currently are: {@linkplain #pan}, {@linkplain #pin}, {@linkplain #cvv2},
 * {@linkplain #expDate} and {@linkplain #fromDate}. The following example will create a very minimal
 * and yet valid instance of {@linkplain ListTransactionsRequest}:
 * <pre>
 *     ListTransactionsRequest request = ListTransactionsRequest.forPan("card_number")
 *                                                              .withPin("your_pin")
 *                                                              .withCvv2("your_cvv2")
 *                                                              .expiresIn("yymm")
 *                                                              .fromDate(new Date())
 *                                                              .build()
 * </pre>
 * If you will, you can chain more filters to the builder before instantiating the actual
 * {@linkplain ListTransactionsRequest}.
 *
 * <p>
 * <h1>Valid time-span</h1>
 * Currently we can only list transactions for at most 30 days starting from the {@linkplain #fromDate}.
 * If you don't set a {@linkplain #toDate}, by default its value would be {@linkplain #fromDate} plus 30
 * days.
 *
 * @author Ali Dehghnai
 */
public class ListTransactionsRequest {
    final String pinType = "EPAY";

    /**
     * (Mandatory) Card number to filter its transactions
     */
    final String pan;

    /**
     * (Mandatory) The card pin
     */
    final String pin;

    /**
     * (Mandatory) CVV2 for the card
     */
    final String cvv2;

    /**
     * (Mandatory) The expiration date of the card in {@code yymm} format in Hejri Shamsi, e.g. 9911.
     */
    final String expDate;

    /**
     * (Mandatory) Setting the starting point for filtering transactions
     */
    final Date fromDate;

    /**
     * (Optional) Setting the ending point of filtering. If not set, it will be {@linkplain #fromDate}
     * plus 30 days
     */
    final Date toDate;

    /**
     * (Optional) Filter card transactions based on the destination pan. Setting this parameter will
     * list only transactions that sent some money to a pan identified by {@linkplain #destinationPan}.
     */
    final String destinationPan;

    /**
     * (Optional) Filter transactions with at least this value in Rials. If given, this value
     * should be less than or equal to the {@linkplain #toAmount}
     */
    final BigDecimal fromAmount;

    /**
     * (Optional) Filter transactions that transfered at most {@linkplain #toAmount} in Rials. If given, it should
     * be more than or equal to {@linkplain #fromDate}
     */
    final BigDecimal toAmount;

    /**
     * (Optional) Filter transactions based on the type of the transaction.
     */
    final Set<TransactionType> transactionTypes;

    /**
     * (Optional) The page size, defaults to 1. If given, it shouldn't be a zero or negative value.
     */
    final Long length;

    /**
     * (Optional) A zero based offset, defaults to 0. If given, it shouldn't be negative value.
     */
    final Long offset;

    private ListTransactionsRequest(String pan, String pin, String cvv2, String expDate, Date fromDate,
                                   Date toDate, String destinationPan, BigDecimal fromAmount, BigDecimal toAmount,
                                   Set<TransactionType> transactionTypes, Long length, Long offset) {

        assertRequiredParams(pan, pin, cvv2, expDate, fromDate);
        assertTimeSpanFilters(fromDate, toDate);
        assertAmountFilters(fromAmount, toAmount);
        assertPaginationParams(length, offset);

        this.pan = pan;
        this.pin = pin;
        this.cvv2 = cvv2;
        this.expDate = expDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.destinationPan = destinationPan;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
        this.transactionTypes = transactionTypes;
        this.length = length;
        this.offset = offset;
    }

    private void assertRequiredParams(String pan, String pin, String cvv2, String expDate, Date fromDate) {
        Asserts.notBlank(pan, "Pan (Card Number) can't be blank string");
        Asserts.notBlank(pin, "Pin (Card Password) can't be a blank string");
        Asserts.notBlank(cvv2, "CVV2 can't be a blank string");
        Asserts.notBlank(expDate, "Expiration date can't be a blank string");
        Asserts.notNull(fromDate, "From date can't be null");
    }

    private void assertTimeSpanFilters(Date fromDate, Date toDate) {
        if (fromDate != null && toDate != null && fromDate.after(toDate))
            throw new IllegalArgumentException("fromDate can't be after toDate");
    }

    private void assertAmountFilters(BigDecimal fromAmount, BigDecimal toAmount) {
        if (fromAmount != null && fromAmount.signum() < 0)
            throw new IllegalArgumentException("fromAmount can't be negative value");

        if (toAmount != null && toAmount.signum() < 0)
            throw new IllegalArgumentException("toAmount can't be negative value");

        if (fromAmount != null && toAmount != null && fromAmount.compareTo(toAmount) > 0)
            throw new IllegalArgumentException("fromAmount can't be bigger than toAmount");
    }

    private void assertPaginationParams(Long length, Long offset) {
        if (length != null && length <= 0)
            throw new IllegalArgumentException("Length can't be less than or equal to zero");

        if (offset != null && offset < 0)
            throw new IllegalArgumentException("Offset can't be a negative value");
    }

    /**
     * A static factory method which is the nexus to the {@linkplain ListTransactionsRequest}'s {@linkplain Builder}.
     *
     * @param pan The pan or card number you wanna list its transactions.
     * @return The fluent {@linkplain Builder}
     */
    public static Builder forPan(String pan) {
        return new Builder().withPan(pan);
    }

    /**
     * A fluent builder for {@linkplain ListTransactionsRequest}. You can chain different building operations together and
     * whenever you feel this is the time, build an instance of {@linkplain ListTransactionsRequest} using the
     * notorious {@linkplain #build()} method.
     *
     * <p>Please note all validations are deferred until you call the {@linkplain #build()} method.
     */
    public static class Builder {
        private String pan;
        private String pin;
        private String cvv2;
        private String expDate;
        private Date fromDate;
        private Date toDate;
        private String destinationPan;
        private BigDecimal fromAmount;
        private BigDecimal toAmount;
        private Set<TransactionType> transactionTypes;
        private Long length;
        private Long offset;

        /**
         * Set the card or PAN number to the given {@code pan}
         *
         * @param pan The card number you want filter its transaction history
         * @return The builder itself
         */
        public Builder withPan(String pan) {
            this.pan = pan;
            return this;
        }

        /**
         * The second or internet password for the card
         *
         * @param pin The card password
         * @return The builder itself
         */
        public Builder withPin(String pin) {
            this.pin = pin;
            return this;
        }

        /**
         * The CVV2 of the card
         *
         * @param cvv2 The cvv2 of the card
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
        public Builder expiresIn(String expDate) {
            this.expDate = expDate;
            return this;
        }

        /**
         * Filter all transactions after the given {@code date}
         *
         * <p>Currently we only can list transactions for 30 days.
         * <p>It should be before than the {@code toDate}, if the {@code toDate} is given.
         *
         * @param date The from date to be set as the starting point of the transaction history
         * @return The builder itself
         */
        public Builder fromDate(Date date) {
            this.fromDate = date;
            return this;
        }

        /**
         * Filter all transactions before the given {@code date}
         *
         * @param date The date to be set as the endpoint of the transaction history
         * @return The builder itself
         */
        public Builder toDate(Date date) {
            this.toDate = date;
            return this;
        }

        /**
         * To filter transactions that send some money to a card identified by {@code destinationPan}
         *
         * @param destinationPan The card number of the destination card
         * @return The builder itself
         */
        public Builder withDestinationPan(String destinationPan) {
            this.destinationPan = destinationPan;
            return this;
        }

        /**
         * Filter transactions with at least {@code fromAmount} as the transaction fee
         *
         * @param fromAmount The amount to be set as the minimum amount of transaction fee in Rials. This
         *                   value should be greater than or equal to zero and be less than {@code toAmount}, if
         *                   that value is set of course.
         * @return The builder itself
         */
        public Builder fromAmount(BigDecimal fromAmount) {
            this.fromAmount = fromAmount;
            return this;
        }

        /**
         * Filter transactions with at most {@code toAmount} as the transaction fee
         *
         * @param toAmount The amount to be set as the maximum amount of transaction fee in Rials. This
         *                 value should be greater than or equal to zero and greater than the {@code fromAmount}, if
         *                 that value is set of course.
         * @return The builder itself
         */
        public Builder toAmount(BigDecimal toAmount) {
            this.toAmount = toAmount;
            return this;
        }

        /**
         * Filter transactions based on the type of the committed transaction.
         *
         * @param type The type of the transaction.
         * @return The builder itself
         */
        public Builder addTransactionType(TransactionType type) {
            if (type != null) {
                if (transactionTypes == null)
                    transactionTypes = new HashSet<>();

                transactionTypes.add(type);
            }

            return this;
        }

        /**
         * Set the size of each page, defaults to 1. This value should be a positive number.
         *
         * @param length The length or size of each page
         * @return The builder itself
         */
        public Builder withLength(Long length) {
            this.length = length;
            return this;
        }

        /**
         * Set offset for pagination
         *
         * @param offset The non-negative offset value
         * @return The builder itself
         */
        public Builder withOffset(Long offset) {
            this.offset = offset;
            return this;
        }

        /**
         * Build the {@linkplain ListTransactionsRequest} out of the current builder.
         *
         * @return An instance of {@linkplain ListTransactionsRequest}
         * @throws IllegalArgumentException If one of the parameters were missing or invalid
         */
        public ListTransactionsRequest build() {
            return new ListTransactionsRequest(
                    pan, pin, cvv2, expDate, fromDate,
                    toDate, destinationPan, fromAmount, toAmount, transactionTypes, length, offset
            );
        }
    }
}