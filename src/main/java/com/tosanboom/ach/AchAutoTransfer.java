package com.tosanboom.ach;

import java.util.List;

/**
 * @author Mona Mohamadinia
 */
public class AchAutoTransfer {
    private String referenceId;
    private String transferDescription;
    private AchTransferStatus status;
    private String sourceIbanNumber;
    private List<AchDestinationAutoTransactionResult> transactions;
    private String currency;

    public String referenceId() {
        return referenceId;
    }

    public String transferDescription() {
        return transferDescription;
    }

    public AchTransferStatus status() {
        return status;
    }

    public String sourceIbanNumber() {
        return sourceIbanNumber;
    }

    public List<AchDestinationAutoTransactionResult> transactions() {
        return transactions;
    }

    public String currency() {
        return currency;
    }

    @Override
    public String toString() {
        return "AchAutoTransfer{" +
                "referenceId='" + referenceId + '\'' +
                ", transferDescription='" + transferDescription + '\'' +
                ", status=" + status +
                ", sourceIbanNumber='" + sourceIbanNumber + '\'' +
                ", transactions=" + transactions +
                ", currency='" + currency + '\'' +
                '}';
    }
}
