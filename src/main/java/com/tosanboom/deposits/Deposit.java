package com.tosanboom.deposits;

import java.math.BigDecimal;

/**
 * Encapsulates a deposit information
 *
 * @author Marjan Mehranfar
 */
public class Deposit {
    private SupportStatus supportStatus;
    private String creditDeposit;
    private Double creditRateAmount;
    private BigDecimal creditRemainAmount;
    private BigDecimal creditLoanRemainAmount;
    private String depositNumber;
    private DepositStatus depositStatus;
    private String inaugurationDate;
    private String expireDate;
    private BigDecimal balance;
    private BigDecimal blockedAmount;
    private String currency;
    private PersonalityType personality;
    private DepositOwnerType owner;
    private SignatureOwnerStatus signature;
    private String depositTitle;
    private String branchCode;
    private DepositGroup group;
    private String supportDepositNumber;
    private DepositStatus supportDepositStatus;
    private String supportCurrency;
    private WithdrawalOption withdrawalOption;
    private BigDecimal availableBalance;
    private BigDecimal extraAvailableBalance;
    private String iban;
    private BigDecimal maximumBalance;
    private BigDecimal minAmountForInterestInclusion;
    private BigDecimal minimumBalance;

    /**
     * The state of support's deposit
     */
    public SupportStatus supportStatus() {
        return supportStatus;
    }

    /**
     * Based on various parameters, banks may consider a credit for each account. This method
     * represents the identifier for this credit.
     */
    public String creditDeposit() {
        return creditDeposit;
    }

    /**
     * The interest rate for {@linkplain #creditDeposit()}
     */
    public Double creditRateAmount() {
        return creditRateAmount;
    }

    /**
     * If the user uses {@linkplain #creditDeposit()}, This parameter determines remain amount of the user's credit.
     */
    public BigDecimal creditRemainAmount() {
        return creditRemainAmount;
    }

    /**
     * If the user uses {@linkplain #creditDeposit()}, This parameter determines remain loan amount of the user's credit.
     */
    public BigDecimal creditLoanRemainAmount() {
        return creditLoanRemainAmount;
    }

    /**
     * The deposit number.
     */
    public String depositNumber() {
        return depositNumber;
    }

    /**
     * The status of deposit, You can refer to {@linkplain DepositStatus}.
     */
    public DepositStatus depositStatus() {
        return depositStatus;
    }

    /**
     * Represents the opening date of a deposit.
     */
    public String inaugurationDate() {
        return inaugurationDate;
    }

    /**
     * It represents date that the deposit will expire.
     */
    public String expireDate() {
        return expireDate;
    }

    /**
     * The real balance of deposit.
     */
    public BigDecimal balance() {
        return balance;
    }

    /**
     * The amount that is blocked in the deposit.
     */
    public BigDecimal blockedAmount() {
        return blockedAmount;
    }

    /**
     * The currency of deposit.
     */
    public String currency() {
        return currency;
    }

    /**
     * It represents the type of personality, You can refer to {@linkplain PersonalityType}.
     */
    public PersonalityType personality() {
        return personality;
    }

    /**
     * Deposit can have different type of owner, Refer to {@linkplain DepositOwnerType}
     */
    public DepositOwnerType owner() {
        return owner;
    }

    /**
     * It represents which of owners having permission of signature.
     */
    public SignatureOwnerStatus signature() {
        return signature;
    }

    /**
     * The title of deposit.
     */
    public String depositTitle() {
        return depositTitle;
    }

    /**
     * The code of branch that the deposit has been opened.
     */
    public String branchCode() {
        return branchCode;
    }

    /**
     * It determines tha type of deposit's group, Refer to {@linkplain DepositGroup}.
     */
    public DepositGroup group() {
        return group;
    }

    /**
     * The support's deposit number
     */
    public String supportDepositNumber() {
        return supportDepositNumber;
    }

    /**
     * The status of support's deposit, Refer to {@linkplain DepositStatus}
     */
    public DepositStatus supportDepositStatus() {
        return supportDepositStatus;
    }

    /**
     * The currency of support's deposit.
     */
    public String supportCurrency() {
        return supportCurrency;
    }

    /**
     * It determines the options for withdrawal, Refer to {@linkplain WithdrawalOption}
     */
    public WithdrawalOption withdrawalOption() {
        return withdrawalOption;
    }

    /**
     * The amount that the user can withdraw.
     */
    public BigDecimal availableBalance() {
        return availableBalance;
    }

    /**
     * This parameter is result of sum  {@link #availableBalance}, the available balance of support's deposit and
     * {@linkplain #creditLoanRemainAmount} provided that includeSupportAccount or includeCreditAccount
     * were be true in the request's parameters.
     */
    public BigDecimal extraAvailableBalance() {
        return extraAvailableBalance;
    }

    /**
     * IBAN of deposit
     */
    public String iban() {
        return iban;
    }

    /**
     * The maximum amount that the deposit can have it.
     */
    public BigDecimal maximumBalance() {
        return maximumBalance;
    }

    /**
     * The minimum amount that the deposit should be have to get interest,
     * provided that the deposit qualifies for interest.
     */
    public BigDecimal minAmountForInterestInclusion() {
        return minAmountForInterestInclusion;
    }

    /**
     * The minimum amount that the deposit should have.
     */
    public BigDecimal minimumBalance() {
        return minimumBalance;
    }

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