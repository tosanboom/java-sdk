package com.tosanboom.cards;

import java.math.BigDecimal;

public class CardBalance {
    BigDecimal availableBalance;
    BigDecimal ledgerBalance;
    String currency;

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(BigDecimal ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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