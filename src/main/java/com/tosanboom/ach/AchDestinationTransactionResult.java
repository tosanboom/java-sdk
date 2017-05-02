package com.tosanboom.ach;

import java.math.BigDecimal;

/**
 * @author Mona Mohamadinia
 */
public class AchDestinationTransactionResult {
    private String id;
    private String ibanNumber;
    private String ownerName;
    private String factorNumber;
    private BigDecimal amount;
    private String description;
    private DestinationTransactionStatus status;
    private AchProblemType problemType;

    public String id() {
        return id;
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

    public DestinationTransactionStatus status() {
        return status;
    }

    public AchProblemType problemType() {
        return problemType;
    }

    @Override
    public String toString() {
        return "AchDestinationTransactionResult{" +
                "id='" + id + '\'' +
                ", ibanNumber='" + ibanNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", factorNumber='" + factorNumber + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", problemType=" + problemType +
                '}';
    }
}
