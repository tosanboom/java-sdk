package com.tosanboom.ach;

import java.math.BigDecimal;

/**
 * Encapsulates response of Ach normal transfer
 *
 * @author Mona Mohamadinia
 */
public class AchNormalTransfer {
    private String transferDescription;
    private String referenceId;
    private String sourceIbanNumber;
    private String currency;
    private AchTransferStatus transferStatus;
    private String ibanNumber;
    private String ownerName;
    private String factorNumber;
    private BigDecimal amount;
    private String description;
    private DestinationTransactionStatus transactionStatus;

    public String transferDescription() {
        return transferDescription;
    }

    public String referenceId() {
        return referenceId;
    }

    public String sourceIbanNumber() {
        return sourceIbanNumber;
    }

    public String currency() {
        return currency;
    }

    public AchTransferStatus transferStatus() {
        return transferStatus;
    }

    public String ibanNumber() {
        return ibanNumber;
    }

    public String ownerName() {
        return ownerName;
    }

    public String factorNumber() {
        return factorNumber;
    }

    public BigDecimal amount() {
        return amount;
    }

    public String description() {
        return description;
    }

    public DestinationTransactionStatus transactionStatus() {
        return transactionStatus;
    }

    @Override
    public String toString() {
        return "AchNormalTransfer{" +
                "transferDescription='" + transferDescription + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", sourceIbanNumber='" + sourceIbanNumber + '\'' +
                ", currency='" + currency + '\'' +
                ", transferStatus=" + transferStatus +
                ", ibanNumber='" + ibanNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", factorNumber='" + factorNumber + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", transactionStatus=" + transactionStatus +
                '}';
    }
}
