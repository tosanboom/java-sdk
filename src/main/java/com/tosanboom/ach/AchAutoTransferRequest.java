package com.tosanboom.ach;

import com.tosanboom.Asserts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Mona Mohamadinia
 */
public class AchAutoTransferRequest {
    /**
     * (Mandatory) The source deposit number for transfer
     */
    final String sourceDepositNumber;

    /**
     * (Mandatory) The iban number, for destination of transfer
     */
    final String ibanNumber;

    /**
     * (Mandatory) The owner name of iban number
     */
    final String ownerName;

    /**
     * (Mandatory) The amount of transfer in Rails
     */
    final BigDecimal amount;

    /**
     * (Optional) Description about the transfer
     */
    final String transferDescription;

    /**
     * (Optional) The date, that user can confirm transaction until it.
     */
    final Date confirmExpireDate;

    /**
     * (Optional) The description
     */
    final String description;

    /**
     * The dates for transactions.
     */
    final List<AchAutoTransactionPeriod> periods;

    private AchAutoTransferRequest(String sourceDepositNumber, String ibanNumber, String ownerName,
                                   BigDecimal amount, String transferDescription, Date confirmExpireDate,
                                   String description, List<AchAutoTransactionPeriod> periods) {

        assertRequiredParams(sourceDepositNumber, ibanNumber, ownerName);
        assertAmountLimitation(amount);
        Asserts.notEmpty(periods, "Periods of transaction list can't be empty");

        this.sourceDepositNumber = sourceDepositNumber;
        this.ibanNumber = ibanNumber;
        this.ownerName = ownerName;
        this.amount = amount;
        this.transferDescription = transferDescription;
        this.confirmExpireDate = confirmExpireDate;
        this.description = description;
        this.periods = periods;
    }

    private void assertRequiredParams(String sourceDepositNumber, String ibanNumber, String ownerName) {
        Asserts.notBlank(sourceDepositNumber, "Source deposit number can't be a blank string");
        Asserts.notBlank(ibanNumber, "Iban number can't be a blank string");
        Asserts.notBlank(ownerName, "Owner name can't be a blank string");
    }

    private void assertAmountLimitation(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0)
            throw new IllegalArgumentException("Amount can't be null or negative");
    }


    /**
     * A static factory method which is the nexus to the {@linkplain AchAutoTransferRequest}'s {@linkplain Builder}.
     *
     * @return The fluent {@linkplain Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * A fluent builder for {@linkplain AchAutoTransferRequest}. You can chain different building operations together and
     * whenever you feel this is the time, build an instance of {@linkplain AchAutoTransferRequest} using the
     * notorious {@linkplain #build()} method.
     */
    public static class Builder {
        private String sourceDepositNumber;
        private String ibanNumber;
        private String ownerName;
        private BigDecimal amount;
        private String transferDescription;
        private Date confirmExpireDate;
        private String description;
        private List<AchAutoTransactionPeriod> periods;

        public Builder withSourceDepositNumber(String sourceDepositNumber) {
            this.sourceDepositNumber = sourceDepositNumber;
            return this;
        }

        public Builder withIbanNumber(String ibanNumber) {
            this.ibanNumber = ibanNumber;
            return this;
        }

        public Builder withOwnerName(String ownerName){
            this.ownerName = ownerName;
            return this;
        }

        public Builder withAmount(BigDecimal amount){
            this.amount = amount;
            return this;
        }

        public Builder withTransferDescription(String transferDescription){
            this.transferDescription = transferDescription;
            return  this;
        }

        public Builder withConfirmExpireDate(Date confirmExpireDate){
            this.confirmExpireDate = confirmExpireDate;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Builder withPeriods(List<AchAutoTransactionPeriod> periods){
            this.periods = periods;
            return this;
        }

        /**
         * Build the {@linkplain AchAutoTransferRequest} out of the current builder.
         *
         * @return An instance of {@linkplain AchAutoTransferRequest}
         * @throws IllegalArgumentException If one of the parameters were missing or invalid
         */
        public AchAutoTransferRequest build(){
            return new AchAutoTransferRequest(sourceDepositNumber, ibanNumber, ownerName, amount,
                                              transferDescription, confirmExpireDate, description, periods);
        }
    }
}
