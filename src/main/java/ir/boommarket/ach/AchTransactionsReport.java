package ir.boommarket.ach;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Mona Mohamadinia
 */
public class AchTransactionsReport {
    private List<AchTransaction> transactions;

    public List<AchTransaction> ransactions() {
        return transactions;
    }

    public static class AchTransaction {
        private String sourceIbanNumber;
        private Boolean resumable;
        private String referenceId;
        private String id;
        private String factorNumber;
        private BigDecimal amount;
        private String currency;
        private String ibanNumber;
        private String ibanOwnerName;
        private Date issueDate;
        private TransactionStatus status;
        private Boolean cancelable;
        private Boolean suspendable;
        private Boolean changeable;
        private String description;

        public String sourceIbanNumber() {
            return sourceIbanNumber;
        }

        public Boolean resumable() {
            return resumable;
        }

        public String referenceId() {
            return referenceId;
        }

        public String id() {
            return id;
        }

        public String factorNumber() {
            return factorNumber;
        }

        public BigDecimal amount() {
            return amount;
        }

        public String currency() {
            return currency;
        }

        public String ibanNumber() {
            return ibanNumber;
        }

        public String ibanOwnerName() {
            return ibanOwnerName;
        }

        public Date issueDate() {
            return issueDate;
        }

        public TransactionStatus status() {
            return status;
        }

        public Boolean cancelable() {
            return cancelable;
        }

        public Boolean suspendable() {
            return suspendable;
        }

        public Boolean changeable() {
            return changeable;
        }

        public String description() {
            return description;
        }

        @Override
        public String toString() {
            return "AchTransaction{" +
                    "sourceIbanNumber='" + sourceIbanNumber + '\'' +
                    ", resumable=" + resumable +
                    ", referenceId='" + referenceId + '\'' +
                    ", id='" + id + '\'' +
                    ", factorNumber='" + factorNumber + '\'' +
                    ", amount=" + amount +
                    ", currency='" + currency + '\'' +
                    ", ibanNumber='" + ibanNumber + '\'' +
                    ", ibanOwnerName='" + ibanOwnerName + '\'' +
                    ", issueDate=" + issueDate +
                    ", status=" + status +
                    ", cancelable=" + cancelable +
                    ", suspendable=" + suspendable +
                    ", changeable=" + changeable +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AchTransactionsReport{" +
                "transactions=" + transactions +
                '}';
    }
}