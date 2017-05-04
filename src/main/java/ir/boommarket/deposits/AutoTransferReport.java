package ir.boommarket.deposits;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marjan Mehranfar
 */
public class AutoTransferReport {
    private List<Report> autoTransferReports = new ArrayList<>();

    public List<Report> autoTransferReports() {
        return autoTransferReports;
    }

    @Override
    public String toString() {
        return "AutoTransferReport{" +
                "autoTransferReports=" + autoTransferReports +
                '}';
    }

    public static class Report {
        private String serial ;
        private String documentNumber;
        private String sourceDepositNumber;
        private String destinationDepositNumber;
        private BigDecimal requestedAmount;
        private BigDecimal transferredAmount;
        private String transactionCount;
        private String transactionDate;
        private String transferredDate;
        private String currency;
        private String status;
        private AutoTransferStatus autoTransferStatus;
        private AutoWithdrawalType autoWithdrawalType;
        private String code;
        private String message;

        /**
         * The serial of auto transfer
         */
        public String serial() {
            return serial;
        }

        /**
         * Represents code of document from one of term's auto transfer
         */
        public String documentNumber() {
            return documentNumber;
        }

        /**
         * The deposit number of source
         */
        public String sourceDepositNumber() {
            return sourceDepositNumber;
        }

        /**
         * The deposit number of destination
         */
        public String destinationDepositNumber() {
            return destinationDepositNumber;
        }

        /**
         *  Represents an amount or a percent of deposit's balance will transfer in each term
         */
        public BigDecimal requestedAmount() {
            return requestedAmount;
        }

        /**
         * The amount transferred
         */
        public BigDecimal transferredAmount() {
            return transferredAmount;
        }

        /**
         * The code to trace
         */
        public String transactionCount() {
            return transactionCount;
        }

        /**
         * The date that auto transfer were registered
         */
        public String transactionDate() {
            return transactionDate;
        }

        /**
         * The date of transferred
         */
        public String transferredDate() {
            return transferredDate;
        }

        /**
         * The type of currency
         */
        public String currency() {
            return currency;
        }

        /**
         * Represents state of auto transferred, It can be {@code success} or {@code failed}
         */
        public String status() {
            return status;
        }

        /**
         * Represents state of auto transferred
         */
        public AutoTransferStatus autoTransferStatus() {
            return autoTransferStatus;
        }

        /**
         * Represents type of withdrawal
         */
        public AutoWithdrawalType autoWithdrawalType() {
            return autoWithdrawalType;
        }

        /**
         * The code of error message
         */
        public String getCode() {
            return code;
        }

        /**
         * The message of error
         */
        public String message() {
            return message;
        }

        @Override
        public String toString() {
            return "Report{" +
                    "serial='" + serial + '\'' +
                    ", documentNumber='" + documentNumber + '\'' +
                    ", sourceDepositNumber='" + sourceDepositNumber + '\'' +
                    ", destinationDepositNumber='" + destinationDepositNumber + '\'' +
                    ", requestedAmount=" + requestedAmount +
                    ", transferredAmount=" + transferredAmount +
                    ", transactionCount='" + transactionCount + '\'' +
                    ", transactionDate='" + transactionDate + '\'' +
                    ", transferredDate='" + transferredDate + '\'' +
                    ", currency='" + currency + '\'' +
                    ", status='" + status + '\'' +
                    ", autoTransferStatus=" + autoTransferStatus +
                    ", autoWithdrawalType=" + autoWithdrawalType +
                    ", code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    /**
     * Represents type of withdrawal
     * <p> It can be:
     * <pre>
     *     PERCENT_STYLE,
     *     PRICE_STYLE,
     *     UNKNOWN
     * </pre>
     */
    private enum AutoWithdrawalType {
        PERCENT_STYLE,
        PRICE_STYLE,
        UNKNOWN
    }
}