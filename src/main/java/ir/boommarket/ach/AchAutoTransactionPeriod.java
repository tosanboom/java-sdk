package ir.boommarket.ach;

import ir.boommarket.Asserts;

import java.util.Date;

/**
 * @author Mona Mohamadinia
 */
public class AchAutoTransactionPeriod {
    final Date issueDate;
    final String factorNumber;

    /**
     * Constructing an instance of {@linkplain AchAutoTransactionPeriod}
     *
     * @param issueDate    The date of transfer
     * @param factorNumber The factor number
     */
    public AchAutoTransactionPeriod(Date issueDate, String factorNumber) {
        Asserts.notNull(issueDate, "Date for transfer can't be null");

        this.issueDate = issueDate;
        this.factorNumber = factorNumber;
    }

    /**
     * Constructing an instance of {@linkplain AchAutoTransactionPeriod}
     *
     * @param issueDate The date of transfer
     */
    public AchAutoTransactionPeriod(Date issueDate) {
        this(issueDate, null);
    }
}
