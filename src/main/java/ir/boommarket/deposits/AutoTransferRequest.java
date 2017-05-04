package ir.boommarket.deposits;

import ir.boommarket.Asserts;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Marjan Mehranfar
 */
public class AutoTransferRequest {
    final BigDecimal amount;
    final String destinationDepositNumber;
    final String sourceDepositNumber;
    final Date startDate;
    final short termLength;
    final Term termType;
    final short transactionCount;
    final long tryCountDay;

    private AutoTransferRequest(BigDecimal amount, String destinationDepositNumber,
                                String sourceDepositNumber, Date startDate, short termLength,
                                Term termType, short transactionCount, long tryCountDay) {
        amountNotZeroOrNegative(amount);
        sourceDepositNotNullOrEmpty(sourceDepositNumber);
        destinationDepositNotNullOrEmpty(destinationDepositNumber);
        startDateNotNull(startDate);
        termLengthNotZero(termLength);
        termTypeNotNull(termType);
        transactionCountNotZero(transactionCount);

        this.amount = amount;
        this.destinationDepositNumber = destinationDepositNumber;
        this.sourceDepositNumber = sourceDepositNumber;
        this.startDate = startDate;
        this.termLength = termLength;
        this.termType = termType;
        this.transactionCount = transactionCount;
        this.tryCountDay = tryCountDay;
    }

    /**
     * @return An instance of {@linkplain Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * A builder for {@linkplain AutoTransferRequest}
     */
    public static class Builder {
        private BigDecimal amount;
        private String destinationDepositNumber;
        private String sourceDepositNumber;
        private Date startDate;
        private short termLength;
        private Term termType;
        private short transactionCount;
        private long tryCountDay;

        /**
         * The amount to transfer
         *
         * @param amount The amount
         */
        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        /**
         * The destination deposit number that receive the amount
         *
         * @param destinationDepositNumber The deposit number of destination
         */
        public Builder withDestinationDepositNumber(String destinationDepositNumber) {
            this.destinationDepositNumber = destinationDepositNumber;
            return this;
        }

        /**
         * The source deposit number will transfer an amount to {@linkplain #destinationDepositNumber}
         *
         * @param sourceDepositNumber The deposit number of source
         */
        public Builder withSourceDepositNumber(String sourceDepositNumber) {
            this.sourceDepositNumber = sourceDepositNumber;
            return this;
        }

        /**
         * The date determines when auto transfer will start.
         *
         * @param startDate The date to start auto transfer
         */
        public Builder withStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        /**
         * {@code termLength} determines count of do auto transfer
         *
         * @param termLength The count of auto transfer
         */
        public Builder withTermLength(short termLength) {
            this.termLength = termLength;
            return this;
        }

        /**
         * It determines the term of auto transfer
         *
         * @param termType the term
         */
        public Builder withTermType(Term termType) {
            this.termType = termType;
            return this;
        }

        /**
         * It determines the count of transaction.
         *
         * @param transactionCount The count
         */
        public Builder withTransactionCount(short transactionCount) {
            this.transactionCount = transactionCount;
            return this;
        }

        /**
         * If auto transfer failed, {@code tryCountDay} determines the count of days try to auto transfer.
         *
         * @param tryCountDay he count of days
         */
        public Builder withTryCountDay(long tryCountDay) {
            this.tryCountDay = tryCountDay;
            return this;
        }

        /**
         * @return An instance of {@linkplain AutoTransferRequest}, It throws an {@linkplain IllegalArgumentException}
         * if one of the given parameters were not valid
         * @throws IllegalArgumentException If given parameters were missing or invalid
         */
        public AutoTransferRequest build() {
            return new AutoTransferRequest(amount, destinationDepositNumber,
                    sourceDepositNumber, startDate, termLength,
                    termType, transactionCount, tryCountDay);
        }
    }

    private void amountNotZeroOrNegative(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0)
            throw new IllegalArgumentException("Amount can not be null or negative");
    }

    private void destinationDepositNotNullOrEmpty(String destinationDepositNumber) {
        Asserts.notBlank(destinationDepositNumber, "DestinationDepositNumber can not be null or a blank string");
    }

    private void sourceDepositNotNullOrEmpty(String sourceDepositNumber) {
        Asserts.notBlank(sourceDepositNumber, "SourceDepositNumber can not be null or a blank string");
    }

    private void startDateNotNull(Date startDate) {
        Asserts.notNull(startDate, "StartDate can not be null");
    }

    private void transactionCountNotZero(short transactionCount) {
        if (transactionCount <= 0)
            throw new IllegalArgumentException("transactionCount can not be zero or a negative value");
    }

    private void termTypeNotNull(Term termType) {
        Asserts.notNull(termType, "TermType can not be a null value");
    }

    private void termLengthNotZero(short termLength) {
        if(termLength <= 0)
            throw new IllegalArgumentException("TermLength can not be zero or a negative value");
    }
}