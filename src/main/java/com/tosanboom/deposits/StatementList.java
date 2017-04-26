package com.tosanboom.deposits;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marjan Mehranfar
 */
public class StatementList {
    @JsonProperty("statemens") // it's a typo and will be fixed in upcoming versions of The REST API
    private List<Statement> statements = new ArrayList<>();

    public List<Statement> statements() {
        return statements;
    }

    public static class Statement {
        private String serialNumber;
        private String date;
        private String description;
        private BigDecimal balance;
        private Long statementSerial;
        private String branchCode;
        private String branchName;
        private String agentBranchCode;
        private String agentBranchName;
        private BigDecimal transferAmount;
        private String referenceNumber;

        public String serialNumber() {
            return serialNumber;
        }

        public String date() {
            return date;
        }

        public String description() {
            return description;
        }

        public BigDecimal balance() {
            return balance;
        }

        public Long statementSerial() {
            return statementSerial;
        }

        public String branchCode() {
            return branchCode;
        }

        public String branchName() {
            return branchName;
        }

        public String agentBranchCode() {
            return agentBranchCode;
        }

        public String agentBranchName() {
            return agentBranchName;
        }

        public BigDecimal transferAmount() {
            return transferAmount;
        }

        public String referenceNumber() {
            return referenceNumber;
        }

        @Override
        public String toString() {
            return "Statement{" +
                    "serialNumber='" + serialNumber + '\'' +
                    ", date='" + date + '\'' +
                    ", description='" + description + '\'' +
                    ", balance=" + balance +
                    ", statementSerial=" + statementSerial +
                    ", branchCode='" + branchCode + '\'' +
                    ", branchName='" + branchName + '\'' +
                    ", agentBranchCode='" + agentBranchCode + '\'' +
                    ", agentBranchName='" + agentBranchName + '\'' +
                    ", transferAmount=" + transferAmount +
                    ", referenceNumber='" + referenceNumber + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "StatementList{" +
                "statements=" + statements +
                '}';
    }
}