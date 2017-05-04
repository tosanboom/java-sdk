package ir.boommarket.ach;

import java.util.List;

/**
 * @author Mona Mohamadinia
 */
public class AchBatchTransfer {
    private String transferDescription;
    private String referenceId;
    private AchTransferStatus status;
    private String sourceIbanNumber;
    private List<AchDestinationTransactionResult> transactions;
    private String currency;

    public String transferDescription() {
        return transferDescription;
    }

    public String referenceId() {
        return referenceId;
    }

    public AchTransferStatus status() {
        return status;
    }

    public String sourceIbanNumber() {
        return sourceIbanNumber;
    }

    public List<AchDestinationTransactionResult> transactions() {
        return transactions;
    }

    public String currency() {
        return currency;
    }

    @Override
    public String toString() {
        return "AchBatchTransfer{" +
                "transferDescription='" + transferDescription + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", status=" + status +
                ", sourceIbanNumber='" + sourceIbanNumber + '\'' +
                ", transactions=" + transactions +
                ", currency='" + currency + '\'' +
                '}';
    }
}
