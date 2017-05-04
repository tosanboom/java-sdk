package ir.boommarket.loans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates details of a specific loan
 *
 * @author Ali Dehghani
 */
public class LoanDetails {
    private String cbLoanNumber;
    private Integer totalRecord;
    private BigDecimal penalty;
    private BigDecimal amount;
    private LoanState state;
    private String currency;
    private BigDecimal discount;
    @JsonProperty("count_of_paid") private Integer paidCount;
    @JsonProperty("count_of_unpaid") private Integer unpaidCount;
    @JsonProperty("count_of_matured_unpaid") private Integer maturedUnpaidCount;
    private BigDecimal totalPaidAmount;
    private BigDecimal totalUnpaidAmount;
    private BigDecimal totalMaturedUnpaidAmount;
    private String automaticPaymentAccountNumber;
    private List<Loan> loanRows = new ArrayList<>();

    public String cbLoanNumber() {
        return cbLoanNumber;
    }

    public Integer totalRecord() {
        return totalRecord;
    }

    public BigDecimal penalty() {
        return penalty;
    }

    public BigDecimal amount() {
        return amount;
    }

    public LoanState state() {
        return state;
    }

    public String currency() {
        return currency;
    }

    public BigDecimal discount() {
        return discount;
    }

    public Integer paidCount() {
        return paidCount;
    }

    public Integer unpaidCount() {
        return unpaidCount;
    }

    public Integer maturedUnpaidCount() {
        return maturedUnpaidCount;
    }

    public BigDecimal totalPaidAmount() {
        return totalPaidAmount;
    }

    public BigDecimal totalUnpaidAmount() {
        return totalUnpaidAmount;
    }

    public BigDecimal totalMaturedUnpaidAmount() {
        return totalMaturedUnpaidAmount;
    }

    public String automaticPaymentAccountNumber() {
        return automaticPaymentAccountNumber;
    }

    public List<Loan> loanRows() {
        return loanRows;
    }

    @Override
    public String toString() {
        return "LoanDetails{" +
                "cbLoanNumber='" + cbLoanNumber + '\'' +
                ", totalRecord=" + totalRecord +
                ", penalty=" + penalty +
                ", amount=" + amount +
                ", state=" + state +
                ", currency='" + currency + '\'' +
                ", discount=" + discount +
                ", paidCount=" + paidCount +
                ", unpaidCount=" + unpaidCount +
                ", maturedUnpaidCount=" + maturedUnpaidCount +
                ", totalPaidAmount=" + totalPaidAmount +
                ", totalUnpaidAmount=" + totalUnpaidAmount +
                ", totalMaturedUnpaidAmount=" + totalMaturedUnpaidAmount +
                ", automaticPaymentAccountNumber='" + automaticPaymentAccountNumber + '\'' +
                ", loanRows=" + loanRows +
                '}';
    }
}