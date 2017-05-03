package com.tosanboom.ach;

import java.math.BigDecimal;

/**
 * @author Mona Mohamadinia
 */
public class AchDestinationAutoTransactionResult {
    private String issueDate;
    private String factorNumber;
    private BigDecimal amount;
    private String description;
    private String ibanNumber;
    private String id;
    private String ownerName;
    private AchProblemType problemType;
    private DestinationTransactionStatus status;

    public String issueDate() {
        return issueDate;
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

    public String ibanNumber() {
        return ibanNumber;
    }

    public String id() {
        return id;
    }

    public String ownerName() {
        return ownerName;
    }

    public AchProblemType problemType() {
        return problemType;
    }

    public DestinationTransactionStatus status() {
        return status;
    }

    @Override
    public String toString() {
        return "AchDestinationAutoTransactionResult{" +
                "issueDate='" + issueDate + '\'' +
                ", factorNumber='" + factorNumber + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", ibanNumber='" + ibanNumber + '\'' +
                ", id='" + id + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", problemType=" + problemType +
                ", status=" + status +
                '}';
    }
}
