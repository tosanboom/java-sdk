package com.tosanboom.loans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Encapsulates loan's owner information such as the customer number
 *
 * @author Ali Dehghani
 */
public class LoanOwner {
    private List<LoanInfo> loansInfo = new ArrayList<>();
    private String customerNumber;
    private String customerFatherName;
    private Date birthDate;
    private String customerCode;
    private String customerName;
    private String ssn;
    private boolean legal;

    public List<LoanInfo> loansInfo() {
        return loansInfo;
    }

    public String customerNumber() {
        return customerNumber;
    }

    public String customerFatherName() {
        return customerFatherName;
    }

    public Date birthDate() {
        return birthDate;
    }

    public String customerCode() {
        return customerCode;
    }

    public String customerName() {
        return customerName;
    }

    public String ssn() {
        return ssn;
    }

    public boolean isLegal() {
        return legal;
    }

    public static class LoanInfo {
        private String loanNumber;
        private String loanIban;

        public String loanNumber() {
            return loanNumber;
        }

        public String loanIban() {
            return loanIban;
        }

        @Override
        public String toString() {
            return "LoanInfo{" +
                    "loanNumber='" + loanNumber + '\'' +
                    ", loanIban='" + loanIban + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoanOwner{" +
                "loansInfo=" + loansInfo +
                ", customerNumber='" + customerNumber + '\'' +
                ", customerFatherName='" + customerFatherName + '\'' +
                ", birthDate=" + birthDate +
                ", customerCode='" + customerCode + '\'' +
                ", customerName='" + customerName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", legal=" + legal +
                '}';
    }
}