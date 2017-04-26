package com.tosanboom.cards;

import com.tosanboom.Asserts;

/**
 * Represents a card balance enquiry request
 *
 * @author Ali Dehghani
 */
public class CardBalanceRequest {
    final String pinType = "EPAY";
    final String pan;
    final String pin;
    final String cvv2;
    final String expDate;

    /**
     * Create an instance of {@linkplain CardBalanceRequest} class to prepare a enquiry request
     * to get balance endpoint
     *
     * @param pan The card or pan number
     * @param pin The second or internet based password of the given card
     * @param cvv2 CVV2 of the card
     * @param expDate Expiration date of the card with {@literal YYMM} format, e.g. {@linkplain 9911}
     * @throws IllegalArgumentException If one of the required parameters were missing or invalid
     */
    public CardBalanceRequest(String pan, String pin, String cvv2, String expDate) {
        validateRequiredParameters(pan, pin, cvv2, expDate);

        this.pan = pan;
        this.pin = pin;
        this.cvv2 = cvv2;
        this.expDate = expDate;
    }

    private void validateRequiredParameters(String pan, String pin, String cvv2, String expDate) {
        Asserts.notBlank(pan, "Pan (Card Number) can't be a blank string");
        Asserts.notBlank(pin, "Pin (Card Password) can't be a blank string");
        Asserts.notBlank(cvv2, "CVV2 can't be a blank string");
        Asserts.notBlank(expDate, "Expiration date can't be a blank string");
    }
}