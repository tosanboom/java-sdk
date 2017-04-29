package com.tosanboom.cards;

import java.math.BigDecimal;

/**
 * Encapsulates response of card transfer
 *
 * @author Mona Mohamadinia
 */
public class CardTransfer {
    private String switchResponseRRN;
    private BigDecimal availableBalance;
    private BigDecimal ledgerBalance;
    private String currency;

    public String switchResponseRRN() {
        return switchResponseRRN;
    }

    public BigDecimal availableBalance() {
        return availableBalance;
    }

    public BigDecimal ledgerBalance() {
        return ledgerBalance;
    }

    public String currency() {
        return currency;
    }

    @Override
    public String toString() {
        return "CardTransfer{" +
                "switchResponseRRN='" + switchResponseRRN + '\'' +
                ", availableBalance=" + availableBalance +
                ", ledgerBalance=" + ledgerBalance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
