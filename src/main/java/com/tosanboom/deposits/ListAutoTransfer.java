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
         * Count of transaction were successful
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
         * filtering list till to end date that they were requested
         */
        public Date endDate() {
            return endDate;
        }

        /**
         * Count of all (auto) transfer requested
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
         * Count of transactions Have not been processed yet
         */
        public Long unProcessedCount() {
            return unProcessedCount;
        }

        /**
         * Count of transactions have been failed
         */
        public Long failedCount() {
            return failedCount;
        }

        /**
         * Count of transactions have been suspended
         */
        public Long suspendedCount() {
            return suspendedCount;
        }

        /**
         * Represents state of auto transferred
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