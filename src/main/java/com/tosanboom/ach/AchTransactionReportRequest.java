package com.tosanboom.ach;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Encapsulates optional parameters that can be used to
 * get reports of ach transaction.
 *
 * @author Mona Mohamadinia
 */
public class AchTransactionReportRequest {
    /**
     * (Optional) The source iban
     */
    final String sourceDepositIban;

    /**
     * (Optional) The transfer description
     */
    final String transferDescription;

    /**
     * (Optional) A zero based offset, defaults to 0. If given, it shouldn't be negative value.
     */
    final Long offset;

    /**
     * (Optional) The page size, defaults to 1. If given, it shouldn't be a zero or negative value.
     */
    final Long length;

    /**
     * (Optional) The reference number of ach transaction.
     */
    final String referenceId;

    /**
     * (Optional) The transaction number of ach transaction
     */
    final String transactionId;

    /**
     * (Optional) The register date of ach transaction.
     */
    final Date fromRegisterDate;

    /**
     * (Optional) The register date of ach transaction.
     */
    final Date toRegisterDate;
    final Date fromIssueDate;
    final Date toIssueDate;
    final BigDecimal fromTransactionAmount;
    final BigDecimal toTransactionAmount;
    final String ibanNumber;
    final String ibanOwnerName;
    final String factorNumber;
    final String description;

    private AchTransactionReportRequest(String sourceDepositIban, String transferDescription, Long offset, Long length,
                                       String referenceId, String transactionId, Date fromRegisterDate,
                                       Date toRegisterDate, Date fromIssueDate, Date toIssueDate,
                                       BigDecimal fromTransactionAmount, BigDecimal toTransactionAmount,
                                       String ibanNumber, String ibanOwnerName, String factorNumber, String description) {

        assertPaginationParams(length, offset);
        assertAmountFilters(fromTransactionAmount, toTransactionAmount);
        assertTimeSpanFilters(fromRegisterDate, toRegisterDate);
        assertTimeSpanFilters(fromIssueDate, toIssueDate);

        this.sourceDepositIban = sourceDepositIban;
        this.transferDescription = transferDescription;
        this.offset = offset;
        this.length = length;
        this.referenceId = referenceId;
        this.transactionId = transactionId;
        this.fromRegisterDate = fromRegisterDate;
        this.toRegisterDate = toRegisterDate;
        this.fromIssueDate = fromIssueDate;
        this.toIssueDate = toIssueDate;
        this.fromTransactionAmount = fromTransactionAmount;
        this.toTransactionAmount = toTransactionAmount;
        this.ibanNumber = ibanNumber;
        this.ibanOwnerName = ibanOwnerName;
        this.factorNumber = factorNumber;
        this.description = description;
    }

    private void assertTimeSpanFilters(Date fromDate, Date toDate) {
        if (fromDate != null && toDate != null && fromDate.after(toDate))
            throw new IllegalArgumentException("From date can't be after to date");
    }

    private void assertAmountFilters(BigDecimal fromTransactionAmount, BigDecimal toTransactionAmount) {
        if (fromTransactionAmount != null && fromTransactionAmount.signum() < 0)
            throw new IllegalArgumentException("From amount can't be negative value");

        if (toTransactionAmount != null && toTransactionAmount.signum() < 0)
            throw new IllegalArgumentException("To amount can't be negative value");

        if (fromTransactionAmount != null && toTransactionAmount != null && fromTransactionAmount.compareTo(toTransactionAmount) > 0)
            throw new IllegalArgumentException("From amount can't be bigger than to amount");
    }

    private void assertPaginationParams(Long length, Long offset) {
        if (length != null && length <= 0)
            throw new IllegalArgumentException("Length can't be less than or equal to zero");

        if (offset != null && offset < 0)
            throw new IllegalArgumentException("Offset can't be a negative value");
    }


    public static AchTransactionReportRequest withoutFilter() {
        return new Builder().build();
    }

    /**
     * A static factory method which is the nexus to the {@linkplain AchTransactionReportRequest}'s {@linkplain Builder}.
     *
     * @return The fluent {@linkplain Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * A fluent builder for {@linkplain AchTransactionReportRequest}. You can chain different building operations together and
     * whenever you feel this is the time, build an instance of {@linkplain AchTransactionReportRequest} using the
     * notorious {@linkplain #build()} method.
     */
    public static class Builder {
        private String sourceDepositIban;
        private String transferDescription;
        private Long offset;
        private Long length;
        private String referenceId;
        private String transactionId;
        private Date fromRegisterDate;
        private Date toRegisterDate;
        private Date fromIssueDate;
        private Date toIssueDate;
        private BigDecimal fromTransactionAmount;
        private BigDecimal toTransactionAmount;
        private String ibanNumber;
        private String ibanOwnerName;
        private String factorNumber;
        private String description;

        public Builder withSourceDepositIban(String sourceDepositIban) {
            this.sourceDepositIban = sourceDepositIban;
            return this;
        }

        public Builder withTransferDescription(String transferDescription) {
            this.transferDescription = transferDescription;
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

        public Builder withReferenceId(String referenceId) {
            this.referenceId = referenceId;
            return this;
        }

        public Builder withTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder withFromTransactionAmount(BigDecimal fromTransactionAmount) {
            this.fromTransactionAmount = fromTransactionAmount;
            return this;
        }

        public Builder withToTransactionAmount(BigDecimal toTransactionAmount) {
            this.toTransactionAmount = toTransactionAmount;
            return this;
        }

        public Builder withIbanNumber(String ibanNumber) {
            this.ibanNumber = ibanNumber;
            return this;
        }

        public Builder withIbanOwnerName(String ibanOwnerName) {
            this.ibanOwnerName = ibanOwnerName;
            return this;
        }

        public Builder withFromRegisterDate(Date fromRegisterDate) {
            this.fromRegisterDate = fromRegisterDate;
            return this;
        }

        public Builder withToRegisterDate(Date toRegisterDate) {
            this.toRegisterDate = toRegisterDate;
            return this;
        }

        public Builder withFromIssueDate(Date fromIssueDate) {
            this.fromIssueDate = fromIssueDate;
            return this;
        }

        public Builder withToIssueDate(Date toIssueDate) {
            this.toIssueDate = toIssueDate;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withFactorNumber(String factorNumber) {
            this.factorNumber = factorNumber;
            return this;
        }

        public AchTransactionReportRequest build() {
            return new AchTransactionReportRequest(sourceDepositIban, transferDescription, offset, length, referenceId,
                    transactionId, fromRegisterDate, toRegisterDate, fromIssueDate, toIssueDate, fromTransactionAmount,
                    toTransactionAmount, ibanNumber, ibanOwnerName, factorNumber, description);
        }
    }
}