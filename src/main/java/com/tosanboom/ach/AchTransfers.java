package com.tosanboom.ach;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Mona Mohamadinia
 */
public class AchTransfers {
    private List<AchTransferReport> achTransferReports = new ArrayList<>();
    private Long totalRecord;

    public List<AchTransferReport> achTransferReports() {
        return achTransferReports;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }

    public static class AchTransferReport {
        private String transferDescription;
        private Boolean resumable;
        private String currency;
        private String referenceId;
        private AchTransferStatus status;
        private Boolean cancelable;
        private Boolean suspendable;
        private Boolean acceptable;
        private String confirmExpireDate;
        private String registerDate;

        public String transferDescription() {
            return transferDescription;
        }

        public Boolean resumable() {
            return resumable;
        }

        public String currency() {
            return currency;
        }

        public String referenceId() {
            return referenceId;
        }

        public AchTransferStatus status() {
            return status;
        }

        public Boolean cancelable() {
            return cancelable;
        }

        public Boolean suspendable() {
            return suspendable;
        }

        public Boolean acceptable() {
            return acceptable;
        }

        public String confirmExpireDate() {
            return confirmExpireDate;
        }

        public String registerDate() {
            return registerDate;
        }

        @Override
        public String toString() {
            return "AchTransferReport{" +
                    "transferDescription='" + transferDescription + '\'' +
                    ", resumable=" + resumable +
                    ", currency='" + currency + '\'' +
                    ", referenceId='" + referenceId + '\'' +
                    ", status=" + status +
                    ", cancelable=" + cancelable +
                    ", suspendable=" + suspendable +
                    ", acceptable=" + acceptable +
                    ", confirmExpireDate='" + confirmExpireDate + '\'' +
                    ", registerDate='" + registerDate + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AchTransfers{" +
                "achTransferReports=" + achTransferReports +
                ", totalRecord=" + totalRecord +
                '}';
    }
}
