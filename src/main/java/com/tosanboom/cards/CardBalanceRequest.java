package com.tosanboom.cards;

public class CardBalanceRequest {
    final String pinType = "EPAY";
    final String pan;
    final String pin;
    final String cvv2;
    final String expDate;

    public CardBalanceRequest(String pan, String pin, String cvv2, String expDate) {
        this.pan = pan;
        this.pin = pin;
        this.cvv2 = cvv2;
        this.expDate = expDate;
    }
}