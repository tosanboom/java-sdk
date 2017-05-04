package ir.boommarket.ach;

import ir.boommarket.Asserts;

import java.math.BigDecimal;

/**
 * @author Mona Mohamadinia
 */
public class AchDestinationTransaction {
    final String ibanNumber;
    final String ownerName;
    final BigDecimal amount;
    final String description;
    final String factorNumber;

    /**
     * Constructing an instance of {@linkplain AchDestinationTransaction}
     *
     * @param ibanNumber   The iban number, for destination of transfer
     * @param ownerName    The owner name of iban number
     * @param amount       The amount of transfer in Rails
     * @param description  The transfer description
     * @param factorNumber The factor number
     */
    public AchDestinationTransaction(String ibanNumber, String ownerName, BigDecimal amount,
                                     String description, String factorNumber) {

        assertRequiredParams(ibanNumber, ownerName);
        assertAmountLimitation(amount);

        this.ibanNumber = ibanNumber;
        this.ownerName = ownerName;
        this.amount = amount;
        this.description = description;
        this.factorNumber = factorNumber;
    }

    /**
     * Constructing an instance of {@linkplain AchDestinationTransaction}
     *
     * @param ibanNumber The iban number, for destination of transfer
     * @param ownerName  The owner name of iban number
     * @param amount     The amount of transfer in Rails
     */
    public AchDestinationTransaction(String ibanNumber, String ownerName, BigDecimal amount) {
        this(ibanNumber, ownerName, amount, null, null);
    }

    /**
     * Constructing an instance of {@linkplain AchDestinationTransaction}
     *
     * @param ibanNumber   The iban number, for destination of transfer
     * @param ownerName    The owner name of iban number
     */
    private void assertRequiredParams(String ibanNumber, String ownerName) {
        Asserts.notBlank(ibanNumber, "Iban number can't be a blank string");
        Asserts.notBlank(ownerName, "owner name can't be a blank string");
    }

    private void assertAmountLimitation(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0)
            throw new IllegalArgumentException("Amount can't be null or negative");
    }
}