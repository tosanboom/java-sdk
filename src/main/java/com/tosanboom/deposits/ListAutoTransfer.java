package com.tosanboom.deposits;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Marjan Mehranfar
 */
public class ListAutoTransfer {
    private List<AutoTransferDetail> autoTransferDetailsList = new ArrayList<>();

    public List<AutoTransferDetail> autoTransferDetailsList() {
        return autoTransferDetailsList;
    }

    @Override
    public String toString() {
        return "ListAutoTransfer{" +
                "autoTransferDetailsList=" + autoTransferDetailsList +
                '}';
    }

    public static class AutoTransferDetail {
        private String sourceDepositNumber;
        private String serial;
        private Short successTransactionNumber;
        private String note;
        private Date startDate;
        private Date endDate;
        private Short transactionCount;
        private boolean disable;
        private String registerDate;
        private Long unProcessedCount;
        private Long failedCount;
        private Long suspendedCount;
        private AutoTransferStatus autoTransferStatus;

        /**
         * The deposit number of source
         */
        public String sourceDepositNumber() {
            return sourceDepositNumber;
        }

        /**
         * The serial of auto transfer
         */
        public String serial() {
            return serial;
        }

        /**
         * Number of transactions were successful
         */
        public Short successTransactionNumber() {
            return successTransactionNumber;
        }

        /**
         * The note of transfer
         */
        public String note() {
            return note;
        }

        /**
         * Filtering list based on date that they were requested
         */
        public Date startDate() {
            return startDate;
        }

        /**
         * Filtering list till to end date that they were requested
         */
        public Date endDate() {
            return endDate;
        }

        /**
         * Number of all (auto) transfers requested
         */
        public Short transactionCount() {
            return transactionCount;
        }

        /**
         * Represents is any transactions were disabled or not
         */
        public boolean disable() {
            return disable;
        }

        /**
         * The date that auto transfer have been registered
         */
        public String registerDate() {
            return registerDate;
        }

        /**
         * Number of transactions have not been processed yet
         */
        public Long unProcessedCount() {
            return unProcessedCount;
        }

        /**
         * Number of transactions have been failed
         */
        public Long failedCount() {
            return failedCount;
        }

        /**
         * Number of transactions have been suspended
         */
        public Long suspendedCount() {
            return suspendedCount;
        }

        /**
         * Represents state of auto transfer
         */
        public AutoTransferStatus autoTransferStatus() {
            return autoTransferStatus;
        }

        @Override
        public String toString() {
            return "AutoTransferDetail{" +
                    "sourceDepositNumber='" + sourceDepositNumber + '\'' +
                    ", serial='" + serial + '\'' +
                    ", successTransactionNumber=" + successTransactionNumber +
                    ", note='" + note + '\'' +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", transactionCount=" + transactionCount +
                    ", disable=" + disable +
                    ", registerDate='" + registerDate + '\'' +
                    ", unProcessedCount=" + unProcessedCount +
                    ", failedCount=" + failedCount +
                    ", suspendedCount=" + suspendedCount +
                    ", autoTransferStatus=" + autoTransferStatus +
                    '}';
        }
    }
}