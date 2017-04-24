package com.tosanboom.deposits;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatementList {
    @JsonProperty("statemens")
    public List<Statement> statements = new ArrayList<>();

    public static class Statement {
        public String serialNumber;
        public String date;
        public String description;
        public BigDecimal balance;
        public Long statementSerial;
        public String branchCode;
        public String branchName;
        public String agentBranchCode;
        public String agentBranchName;
        public BigDecimal transferAmount;
        public String referenceNumber;
        public String terminalID;

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
                    ", terminalID='" + terminalID + '\'' +
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
