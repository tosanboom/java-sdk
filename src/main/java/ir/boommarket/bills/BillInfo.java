package ir.boommarket.bills;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Encapsulate bill information.
 *
 * @author Mona Mohamadinia
 */
public class BillInfo {
    private BillType type;
    private BigDecimal amount;
    private String title;
    private String foreignTitle;
    private boolean paid;
    private Date payDate;
    private String referenceNumber;

    public BillType type() {
        return type;
    }

    public BigDecimal amount() {
        return amount;
    }

    public String title() {
        return title;
    }

    public String foreignTitle() {
        return foreignTitle;
    }

    public boolean paid() {
        return paid;
    }

    public Date payDate() {
        return payDate;
    }

    public String referenceNumber() {
        return referenceNumber;
    }

    @Override
    public String toString() {
        return "BillInfo{" +
                "type=" + type +
                ", amount=" + amount +
                ", title='" + title + '\'' +
                ", foreignTitle='" + foreignTitle + '\'' +
                ", paid=" + paid +
                ", payDate='" + payDate + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                '}';
    }
}
