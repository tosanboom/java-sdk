package com.tosanboom.deposits;

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

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withDestinationDepositNumber(String destinationDepositNumber) {
            this.destinationDepositNumber = destinationDepositNumber;
            return this;
        }

        public Builder withSourceDepositNumber(String sourceDepositNumber) {
            this.sourceDepositNumber = sourceDepositNumber;
            return this;
        }

        public Builder withStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withTermLength(short termLength) {
            this.termLength = termLength;
            return this;
        }

        public Builder withTermType(Term termType) {
            this.termType = termType;
            return this;
        }

        public Builder withTransactionCount(short transactionCount) {
            this.transactionCount = transactionCount;
            return this;
        }

        public Builder withTryCountDay(long tryCountDay) {
            this.tryCountDay = tryCountDay;
            return this;
        }

        /**
         * @return An instance of {@linkplain AutoTransferRequest}, It throws an {@linkplain IllegalArgumentException}
         * if one of the given parameters were not valid
         */
        public AutoTransferRequest build() {
            return new AutoTransferRequest(amount, destinationDepositNumber,
                    sourceDepositNumber, startDate, termLength,
                    termType, transactionCount, tryCountDay);
        }
    }

    private void amountNotZeroOrNegative(BigDecimal amount) {
        if(amount == null || amount.signum() <= 0)
            throw new IllegalArgumentException("Amount can not be null or negative");
    }

    private void destinationDepositNotNullOrEmpty(String destinationDepositNumber) {
        if(destinationDepositNumber == null || destinationDepositNumber.trim().isEmpty())
            throw new IllegalArgumentException("DestinationDepositNumber can not be null or a blank string");
    }

    private void sourceDepositNotNullOrEmpty(String sourceDepositNumber) {
        if(sourceDepositNumber == null || sourceDepositNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("SourceDepositNumber can not be null or a blank string");
        }
    }

    private void startDateNotNull(Date startDate) {
        if(startDate ==null)
            throw new IllegalArgumentException("StartDate can not be null");
    }

    private void transactionCountNotZero(short transactionCount) {
        if(transactionCount <= 0)
            throw new IllegalArgumentException("transactionCount can not be zero or a negative value");
    }

    private void termTypeNotNull(Term termType) {
        if(termType == null)
            throw new IllegalArgumentException("TermType can not be a null value");
    }

    private void termLengthNotZero(short termLength) {
        if(termLength <= 0)
            throw new IllegalArgumentException("TermLength can not be zero or a negative value");
    }
}