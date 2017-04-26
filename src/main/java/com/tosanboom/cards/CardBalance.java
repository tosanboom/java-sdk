package com.tosanboom.cards;

import java.math.BigDecimal;

/**
 * Represents the balance of a card
 *
 * @author Ali Dehghani
 */
public class CardBalance {
    public BigDecimal availableBalance;
    public BigDecimal ledgerBalance;
    public String currency;

    @Override
    public String toString() {
        return "CardBalance{" +
                "availableBalance=" + availableBalance +
                ", ledgerBalance=" + ledgerBalance +
                ", currency='" + currency + '\'' +
                '}';
    }
}