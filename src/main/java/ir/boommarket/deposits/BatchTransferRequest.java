package ir.boommarket.deposits;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.boommarket.Asserts;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Marjan Mehranfar
 */
public class BatchTransferRequest {
    final String sourceDepositNumber;
    @JsonProperty("destination_batch_transfer") final List<TransferDestination> destinationBatchTransfers;
    public String sourceDescription;
    public boolean ignoreError = false;

    /**
     * An instance of {@linkplain BatchTransferRequest} with required parameters
     *
     * @param sourceDepositNumber (Mandatory) The deposit number of source to (batch) transfer
     * @param destinationBatchTransfers (Mandatory) List of information of destinations to transfer an amount of money to them
     * @throws IllegalArgumentException If one of parameters weren't valid
     */
    public BatchTransferRequest(String sourceDepositNumber, List<TransferDestination> destinationBatchTransfers) {
        this(sourceDepositNumber, destinationBatchTransfers, null, false);
    }

    /**
     * An instance of {@linkplain BatchTransferRequest} with required and optional parameters
     *
     * @param sourceDepositNumber (Mandatory) The deposit number of source to (batch) transfer
     * @param destinationBatchTransfers (Mandatory) List of information of destinations to transfer an amount of money to them
     * @param sourceDescription (Optional) The description that fill in the source to transfer,
     *                          it will show up in get statements for source deposit
     * @param ignoreError (Optional) Determines whether failure of one transaction should rollback others or not. If sets
     *                    {@code false}, if one of the given transactions got failed for whatever reason, previously
     *                    successful transactions will be rolled back and the rest will be ignored
     * @throws IllegalArgumentException If one of parameters weren't valid
     */
    public BatchTransferRequest(String sourceDepositNumber, List<TransferDestination> destinationBatchTransfers,
                                String sourceDescription, boolean ignoreError) {

        Asserts.notBlank(sourceDepositNumber, "The sourceDepositNumber can not be null or a blank string");
        destinationsNotBeNullOrEmpty(destinationBatchTransfers);

        this.sourceDepositNumber = sourceDepositNumber;
        this.sourceDescription = sourceDescription;
        this.destinationBatchTransfers = destinationBatchTransfers;
        this.ignoreError = ignoreError;
    }

    /**
     * {@linkplain TransferDestination}  encapsulates information of destination to transfer
     * such as {@code destinationDepositNumber}, {@code amount} and {@code description}.
     * This class has two constructors that {@code destinationDepositNumber} and {@code amount} are required parameters,
     * If they weren't valid it throws {@linkplain IllegalArgumentException}.
     */
    public static class TransferDestination {
        final String destinationDepositNumber;
        final BigDecimal amount;
        public String description;

        /**
         * Encapsulates required parameters to transfer an amount of money
         *
         * @param destinationDepositNumber (Mandatory) The deposit number of destination
         * @param amount (Mandatory) The amount of money to transfer
         */
        public TransferDestination(String destinationDepositNumber, BigDecimal amount) {
            this(destinationDepositNumber, amount, null);
        }

        /**
         * Encapsulates required and optional parameters to transfer an amount of money
         *
         * @param destinationDepositNumber (Mandatory) The deposit number of destination
         * @param amount (Mandatory) The amount of money to transfer
         * @param description (Optional) The description of transfer
         */
        public TransferDestination(String destinationDepositNumber, BigDecimal amount, String description) {
            validationParameters(destinationDepositNumber, amount);

            this.destinationDepositNumber = destinationDepositNumber;
            this.amount = amount;
            this.description = description;
        }

        private void validationParameters(String destinationDepositNumber, BigDecimal amount) {
            Asserts.notBlank(destinationDepositNumber, "The destinationDepositNumber can not be null or a blank string");

            if (amount == null || amount.signum() <= 0)
                throw new IllegalArgumentException("The amount can not be null or a negative value");
        }
    }

    private void destinationsNotBeNullOrEmpty(List<TransferDestination> destinationBatchTransfers) {
        if (destinationBatchTransfers == null || destinationBatchTransfers.isEmpty())
            throw new IllegalArgumentException("List of destinationBatchTransfer can not be null or empty");
    }
}