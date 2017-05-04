package ir.boommarket.cards;

import java.math.BigDecimal;

/**
 * Represents the balance of a card
 *
 * @author Ali Dehghani
 */
public class CardBalance {
    private BigDecimal availableBalance;
    private BigDecimal ledgerBalance;
    private String currency;

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
        return "CardBalance{" +
                "availableBalance=" + availableBalance +
                ", ledgerBalance=" + ledgerBalance +
                ", currency='" + currency + '\'' +
                '}';
    }
}