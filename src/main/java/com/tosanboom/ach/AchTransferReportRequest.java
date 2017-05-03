package com.tosanboom.ach;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Mona Mohamadinia
 */
public class AchTransferReportRequest {
    final String sourceDepositIban;
    final String transferDescription;
    final Long offset;
    final Long length;
    final BigDecimal fromTransactionAmount;
    final BigDecimal toTransactionAmount;
    final String referenceId;
    final String destinationIbanNumber;
    final String destinationOwnerName;
    final Date fromRegisterDate;
    final Date toRegisterDate;
    final Date fromIssueDate;
    final Date toIssueDate;
    final String description;
    final String factorNumber;
    final List<AchTransferStatus> statusSet;
    final List<TransactionStatus> transactionStatusSet;


    private AchTransferReportRequest(String sourceDepositIban, String transferDescription, Long offset, Long length,
                                     BigDecimal fromTransactionAmount, BigDecimal toTransactionAmount,
                                     String referenceId, String destinationIbanNumber, String destinationOwnerName,
                                     Date fromRegisterDate, Date toRegisterDate, Date fromIssueDate,
                                     Date toIssueDate, String description, String factorNumber,
                                     List<AchTransferStatus> statusSet, List<TransactionStatus> transactionStatusSet) {

        assertPaginationParams(length, offset);
        assertAmountFilters(fromTransactionAmount, toTransactionAmount);
        assertTimeSpanFilters(fromRegisterDate, toRegisterDate);
        assertTimeSpanFilters(fromIssueDate, toIssueDate);

        this.sourceDepositIban = sourceDepositIban;
        this.transferDescription = transferDescription;
        this.offset = offset;
        this.length = length;
        this.fromTransactionAmount = fromTransactionAmount;
        this.toTransactionAmount = toTransactionAmount;
        this.referenceId = referenceId;
        this.destinationIbanNumber = destinationIbanNumber;
        this.destinationOwnerName = destinationOwnerName;
        this.fromRegisterDate = fromRegisterDate;
        this.toRegisterDate = toRegisterDate;
        this.fromIssueDate = fromIssueDate;
        this.toIssueDate = toIssueDate;
        this.description = description;
        this.factorNumber = factorNumber;
        this.statusSet = statusSet;
        this.transactionStatusSet = transactionStatusSet;
    }

    private void assertTimeSpanFilters(Date fromDate, Date toDate) {
        if (fromDate != null && toDate != null && fromDate.after(toDate))
            throw new IllegalArgumentException("fromDate can't be after toDate");
    }

    private void assertAmountFilters(BigDecimal fromTransactionAmount, BigDecimal toTransactionAmount) {
        if (fromTransactionAmount != null && fromTransactionAmount.signum() < 0)
            throw new IllegalArgumentException("fromAmount can't be negative value");

        if (toTransactionAmount != null && toTransactionAmount.signum() < 0)
            throw new IllegalArgumentException("toAmount can't be negative value");

        if (fromTransactionAmount != null && toTransactionAmount != null && fromTransactionAmount.compareTo(toTransactionAmount) > 0)
            throw new IllegalArgumentException("fromAmount can't be bigger than toAmount");
    }

    private void assertPaginationParams(Long length, Long offset) {
        if (length != null && length <= 0)
            throw new IllegalArgumentException("Length can't be less than or equal to zero");

        if (offset != null && offset < 0)
            throw new IllegalArgumentException("Offset can't be a negative value");
    }

    public static AchTransferReportRequest withoutFilter() {
        return new Builder().build();
    }
    /**
     * A static factory method which is the nexus to the {@linkplain AchTransferReportRequest}'s {@linkplain Builder}.
     *
     * @return The fluent {@linkplain Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * A fluent builder for {@linkplain AchTransferReportRequest}. You can chain different building operations together and
     * whenever you feel this is the time, build an instance of {@linkplain AchTransferReportRequest} using the
     * notorious {@linkplain #build()} method.
     */
    public static class Builder {
        private String sourceDepositIban;
        private String transferDescription;
        private Long offset;
        private Long length;
        private BigDecimal fromTransactionAmount;
        private BigDecimal toTransactionAmount;
        private String referenceId;
        private String destinationIbanNumber;
        private String destinationOwnerName;
        private Date fromRegisterDate;
        private Date toRegisterDate;
        private Date fromIssueDate;
        private Date toIssueDate;
        private String description;
        private String factorNumber;
        private List<AchTransferStatus> statusSet;
        private List<TransactionStatus> transactionStatusSet;

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

        public Builder withFromTransactionAmount(BigDecimal fromTransactionAmount) {
            this.fromTransactionAmount = fromTransactionAmount;
            return this;
        }

        public Builder withToTransactionAmount(BigDecimal toTransactionAmount) {
            this.toTransactionAmount = toTransactionAmount;
            return this;
        }

        public Builder withReferenceId(String referenceId){
            this.referenceId = referenceId;
            return this;
        }

        public Builder withDestinationIbanNumber(String destinationIbanNumber){
             this.destinationIbanNumber = destinationIbanNumber;
             return this;
         }

        public Builder withDestinationOwnerName(String destinationOwnerName){
            this.destinationOwnerName = destinationOwnerName;
            return this;
        }

        public Builder withFromRegisterDate(Date fromRegisterDate){
            this.fromRegisterDate = fromRegisterDate;
            return this;
        }

        public Builder withToRegisterDate(Date toRegisterDate){
            this.toRegisterDate = toRegisterDate;
            return this;
        }

        public Builder withFromIssueDate(Date fromIssueDate){
            this.fromIssueDate = fromIssueDate;
            return this;
        }

        public Builder withToIssueDate(Date toIssueDate){
            this.toIssueDate = toIssueDate;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Builder withFactorNumber(String factorNumber){
            this.factorNumber = factorNumber;
            return this;
        }

        public Builder withStatusSet(List<AchTransferStatus> status){
            this.statusSet = status;
            return this;
        }

        public Builder withTransactionStatusSet(List<TransactionStatus> transactionStatusSet){
            this.transactionStatusSet = transactionStatusSet;
            return this;
        }

        public AchTransferReportRequest build(){
            return new AchTransferReportRequest(sourceDepositIban, transferDescription, offset, length,
                                                fromTransactionAmount, toTransactionAmount, referenceId,
                                                destinationIbanNumber, destinationOwnerName, fromRegisterDate,
                                                toRegisterDate, fromIssueDate, toIssueDate, description, factorNumber,
                                                statusSet, transactionStatusSet);
        }
    }
}