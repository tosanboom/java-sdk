package com.tosanboom.ach;

import com.tosanboom.Asserts;

import java.math.BigDecimal;

/**
 * Encapsulates mandatory/optional parameters that must/can be used to
 * transfer amount through Ach normal transfer
 *
 * @author Mona Mohamadinia
 */
public class AchNormalTransferRequest {
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
     * (Optional) The description
     */
    final String description;

    /**
     * (Optional) Description about the transfer
     */
    final String transferDescription;

    /**
     * (Optional) The factor number
     */
    final String factorNumber;

    private AchNormalTransferRequest(String transferDescription, String sourceDepositNumber, String ibanNumber,
                                     String ownerName, String description, BigDecimal amount, String factorNumber) {

        assertRequiredParams(sourceDepositNumber, ibanNumber, ownerName);
        assertAmountLimitation(amount);

        this.transferDescription = transferDescription;
        this.sourceDepositNumber = sourceDepositNumber;
        this.ibanNumber = ibanNumber;
        this.ownerName = ownerName;
        this.description = description;
        this.amount = amount;
        this.factorNumber = factorNumber;
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
     * A static factory method which is the nexus to the {@linkplain AchNormalTransferRequest}'s {@linkplain Builder}.
     *
     * @return The fluent {@linkplain Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * A fluent builder for {@linkplain AchNormalTransferRequest}. You can chain different building operations together and
     * whenever you feel this is the time, build an instance of {@linkplain AchNormalTransferRequest} using the
     * notorious {@linkplain #build()} method.
     */
    public static class Builder {
        private String transferDescription;
        private String sourceDepositNumber;
        private String ibanNumber;
        private String ownerName;
        private String description;
        private BigDecimal amount;
        private String factorNumber;

        /**
         * Set the transfer description
         *
         * @param transferDescription The transfer description
         * @return The builder itself
         */
        public Builder withTransferDescription(String transferDescription) {
            this.transferDescription = transferDescription;
            return this;
        }

        /**
         * Set the source deposit number for transfer
         *
         * @param sourceDepositNumber The source deposit number for transfer
         * @return The builder itself
         */
        public Builder withSourceDepositNumber(String sourceDepositNumber) {
            this.sourceDepositNumber = sourceDepositNumber;
            return this;
        }

        /**
         * Set the iban number, for destination of transfer
         *
         * @param ibanNumber The iban number, for destination of transfer
         * @return The builder itself
         */
        public Builder withIbanNumber(String ibanNumber) {
            this.ibanNumber = ibanNumber;
            return this;
        }

        /**
         * Set the owner name of iban number
         *
         * @param ownerName The owner name of iban number
         * @return The builder itself
         */
        public Builder withOwnerName(String ownerName) {
            this.ownerName = ownerName;
            return this;
        }

        /**
         * Set the description
         *
         * @param description The description
         * @return The builder itself
         */
        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Set the amount of transfer in Rails
         *
         * @param amount The amount of transfer in Rails
         * @return The builder itself
         */
        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Set the factor number
         *
         * @param factorNumber The factor number
         * @return The builder itself
         */
        public Builder withFactorNumber(String factorNumber) {
            this.factorNumber = factorNumber;
            return this;
        }

        /**
         * Build the {@linkplain AchNormalTransferRequest} out of the current builder.
         *
         * @return An instance of {@linkplain AchNormalTransferRequest}
         * @throws IllegalArgumentException If one of the parameters were missing or invalid
         */
        public AchNormalTransferRequest build() {
            return new AchNormalTransferRequest(transferDescription, sourceDepositNumber, ibanNumber, ownerName,
                    description, amount, factorNumber);
        }
    }
}
