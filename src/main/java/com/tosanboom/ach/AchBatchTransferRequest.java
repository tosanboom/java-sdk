package com.tosanboom.ach;

import com.tosanboom.Asserts;

import java.util.List;

/**
 * Encapsulates mandatory/optional parameters that must/can be used to
 * transfer amount through Ach batch transfer
 *
 * @author Mona Mohamadinia
 */
public class AchBatchTransferRequest {
    final String sourceDepositNumber;
    final List<AchDestinationTransaction> transactions;
    final String transferDescription;
    final boolean ignoreError;

    /**
     * Constructing an instance of {@linkplain AchBatchTransferRequest}
     *
     * @param sourceDepositNumber The source deposit number of transaction
     * @param transactions        Details of each transfer.
     * @param transferDescription The transfer description
     * @param ignoreError         If set false, Ignoring other transaction if one of them failed
     *                            If set true, process others.
     */
    public AchBatchTransferRequest(String sourceDepositNumber, List<AchDestinationTransaction> transactions,
                                   String transferDescription, boolean ignoreError) {
        Asserts.notBlank(sourceDepositNumber, "Source deposit number can't be a blank string");
        Asserts.notEmpty(transactions, "List of transaction can't be empty");

        this.sourceDepositNumber = sourceDepositNumber;
        this.transactions = transactions;
        this.transferDescription = transferDescription;
        this.ignoreError = ignoreError;
    }

    /**
     * Constructing an instance of {@linkplain AchBatchTransferRequest}
     *
     * @param sourceDepositNumber The source deposit number of transaction
     * @param transactions        Details of each transfer.
     */
    public AchBatchTransferRequest(String sourceDepositNumber, List<AchDestinationTransaction> transactions) {
        this(sourceDepositNumber, transactions, null, true);
    }

    /**
     * Constructing an instance of {@linkplain AchBatchTransferRequest}
     *
     * @param sourceDepositNumber The source deposit number of transaction
     * @param transactions        Details of each transfer.
     * @param transferDescription The transfer description
     */
    public AchBatchTransferRequest(String sourceDepositNumber, List<AchDestinationTransaction> transactions,
                                   String transferDescription) {
        this(sourceDepositNumber, transactions, transferDescription, true);
    }

    /**
     * Constructing an instance of {@linkplain AchBatchTransferRequest}
     *
     * @param sourceDepositNumber The source deposit number of transaction
     * @param transactions        Details of each transfer.
     * @param ignoreError         If set false, Ignoring other transaction if one of them failed
     *                            If set true, process others.
     */
    public AchBatchTransferRequest(String sourceDepositNumber, List<AchDestinationTransaction> transactions,
                                   boolean ignoreError) {
        this(sourceDepositNumber, transactions, null, ignoreError);
    }
}