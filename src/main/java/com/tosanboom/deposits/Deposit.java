package com.tosanboom.deposits;

import java.math.BigDecimal;

/**
 * @author Marjan Mehranfar
 */
public class Deposit {
    public SupportStatus supportStatus;
    public String creditDeposit;
    public Double creditRateAmount;
    public BigDecimal creditRemainAmount;
    public BigDecimal creditLoanRemainAmount;
    public String depositNumber;
    public DepositStatus depositStatus;
    public String inaugurationDate;
    public String expireDate;
    public BigDecimal balance;
    public BigDecimal blockedAmount;
    public String currency;
    public PersonalityType personality;
    public DepositOwnerType owner;
    public SignatureOwnerStatus signature;
    public String depositTitle;
    public String branchCode;
    public DepositGroup group;
    public String supportDepositNumber;
    public DepositStatus supportDepositStatus;
    public String supportCurrency;
    public WithdrawalOption withdrawalOption;
    public BigDecimal availableBalance;
    public BigDecimal extraAvailableBalance;
    public String iban;
    public BigDecimal maximumBalance;
    public BigDecimal minAmountForInterestInclusion;
    public BigDecimal minimumBalance;

    @Override
    public String toString() {
        return "Deposit{" +
                "supportStatus=" + supportStatus +
                ", creditDeposit='" + creditDeposit + '\'' +
                ", creditRateAmount=" + creditRateAmount +
                ", creditRemainAmount=" + creditRemainAmount +
                ", creditLoanRemainAmount=" + creditLoanRemainAmount +
                ", depositNumber='" + depositNumber + '\'' +
                ", depositStatus=" + depositStatus +
                ", inaugurationDate='" + inaugurationDate + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", balance=" + balance +
                ", blockedAmount=" + blockedAmount +
                ", currency='" + currency + '\'' +
                ", personality=" + personality +
                ", owner=" + owner +
                ", signature=" + signature +
                ", depositTitle='" + depositTitle + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", group=" + group +
                ", supportDepositNumber='" + supportDepositNumber + '\'' +
                ", supportDepositStatus=" + supportDepositStatus +
                ", supportCurrency='" + supportCurrency + '\'' +
                ", withdrawalOption=" + withdrawalOption +
                ", availableBalance=" + availableBalance +
                ", extraAvailableBalance=" + extraAvailableBalance +
                ", iban='" + iban + '\'' +
                ", maximumBalance=" + maximumBalance +
                ", minAmountForInterestInclusion=" + minAmountForInterestInclusion +
                ", minimumBalance=" + minimumBalance +
                '}';
    }
}