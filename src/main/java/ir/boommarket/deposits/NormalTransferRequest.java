package ir.boommarket.deposits;

import java.math.BigDecimal;

/**
 * @author Marjan Mehranfar
 */
public class NormalTransferRequest {
    final String sourceDeposit;
    final String destinationDeposit;
    final String sourceComment;
    final String destinationComment;
    final String referenceNumber;
    final BigDecimal amount;

    private NormalTransferRequest(String sourceDeposit, String destinationDeposit, String sourceComment,
                                  String destinationComment, String referenceNumber, BigDecimal amount) {

        notNullOrEmptyString(sourceDeposit);
        notNullOrEmptyString(destinationDeposit);
        amountNotNegativeOrZero(amount);

        this.sourceDeposit = sourceDeposit;
        this.destinationDeposit = destinationDeposit;
        this.sourceComment = sourceComment;
        this.destinationComment = destinationComment;
        this.referenceNumber = referenceNumber;
        this.amount = amount;
    }

    /**
     * Return an instance of Builder
     *
     * @return {@linkplain NormalTransferRequest.Builder}
     */
    public static Builder newBuilder() {
        return new NormalTransferRequest.Builder();
    }

    /**
     * A builder for {@linkplain NormalTransferRequest} calss
     */
    public static class Builder {
        private String sourceDeposit;
        private String destinationDeposit;
        private String sourceComment;
        private String destinationComment;
        private String referenceNumber;
        private BigDecimal amount;

        /**
         * (Mandatory) Deposit number of the source of transfer
         *
         * @param sourceDeposit The deposit number of source
         */
        public Builder withSourceDeposit(String sourceDeposit) {
            this.sourceDeposit = sourceDeposit;
            return this;
        }

        /**
         * (Mandatory) deposit number of destination to receive an amount from {@linkplain #sourceDeposit}.
         *
         * @param destinationDeposit The deposit number of destination
         */
        public Builder withDestinationDeposit(String destinationDeposit) {
            this.destinationDeposit = destinationDeposit;
            return this;
        }

        /**
         * (Optional) The description that only source of transaction can see
         *
         * @param sourceComment The description from source
         */
        public Builder withSourceComment(String sourceComment) {
            this.sourceComment = sourceComment;
            return this;
        }

        /**
         * (Optional) Description that will be received from the receiver of amount.
         *
         * @param destinationComment The description that received by destination
         */
        public Builder withDestinationComment(String destinationComment) {
            this.destinationComment = destinationComment;
            return this;
        }

        /**
         * Reference number of transaction. If given, it should be unique and the responsibility to be unique
         * depends on client that transfers an amount to the destination deposit number.
         *
         * @param referenceNumber The Reference number of transaction
         */
        public Builder withReferenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
            return this;
        }

        /**
         * The amount to transfer between source and destination deposit numbers
         *
         * @param amount The amount
         */
        public Builder withAmount(BigDecimal amount) {
           this.amount =amount;
            return this;
        }

        /**
         *
         * @throws IllegalArgumentException If one of the given parameters were't valid
         */
        public NormalTransferRequest build() {
            return new NormalTransferRequest(sourceDeposit, destinationDeposit, sourceComment,
                    destinationComment, referenceNumber, amount);
        }
    }

    private void notNullOrEmptyString(String sourceDeposit) {
        if (sourceDeposit == null || sourceDeposit.trim().isEmpty())
            throw new IllegalArgumentException("SourceDeposit can not be null or a blank string");
    }

    private void amountNotNegativeOrZero(BigDecimal amount) {
        if (amount ==null || amount.signum() <= 0)
            throw new IllegalArgumentException("Amount can't be null or negative");
    }
}