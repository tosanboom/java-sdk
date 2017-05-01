package com.tosanboom.bills;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mona Mohamadinia
 */
public class BillPayment {
    private BigDecimal amount;
    private String billId;
    private String billTitle;
    private BillType billType;
    private String payId;
    private String fileCode;
    private String cycle;
    private String referralNumber;
    private Date date;
    private Date lastLoginDate;

    public BigDecimal amount() {
        return amount;
    }

    public String billId() {
        return billId;
    }

    public String billTitle() {
        return billTitle;
    }

    public BillType billType() {
        return billType;
    }

    public String payId() {
        return payId;
    }

    public String fileCode() {
        return fileCode;
    }

    public String cycle() {
        return cycle;
    }

    public String referralNumber() {
        return referralNumber;
    }

    public Date date() {
        return date;
    }

    public Date lastLoginDate() {
        return lastLoginDate;
    }

    @Override
    public String toString() {
        return "BillPayment{" +
                "amount=" + amount +
                ", billId='" + billId + '\'' +
                ", billTitle='" + billTitle + '\'' +
                ", billType=" + billType +
                ", payId='" + payId + '\'' +
                ", fileCode='" + fileCode + '\'' +
                ", cycle='" + cycle + '\'' +
                ", referralNumber='" + referralNumber + '\'' +
                ", date='" + date + '\'' +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                '}';
    }
}
