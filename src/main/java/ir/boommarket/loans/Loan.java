package ir.boommarket.loans;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Encapsulates each loan payment details such as the date the payment
 * committed
 *
 * @author Ali Dehghani
 */
public class Loan {
    private BigDecimal penaltyAmount;
    private BigDecimal unpaidAmount;
    private BigDecimal payedAmount;
    private Integer delayDay;
    private Date payDate;
    private PayStatus payStatus;

    public BigDecimal penaltyAmount() {
        return penaltyAmount;
    }

    public BigDecimal unpaidAmount() {
        return unpaidAmount;
    }

    public BigDecimal payedAmount() {
        return payedAmount;
    }

    public Integer delayDay() {
        return delayDay;
    }

    public Date payDate() {
        return payDate;
    }

    public PayStatus payStatus() {
        return payStatus;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "penaltyAmount=" + penaltyAmount +
                ", unpaidAmount=" + unpaidAmount +
                ", payedAmount=" + payedAmount +
                ", delayDay=" + delayDay +
                ", payDate=" + payDate +
                ", payStatus=" + payStatus +
                '}';
    }
}