package com.tosanboom.cards;

/**
 * To call cardHolder service these parameters will be send
 * All of them are required except destinationPan
 *
 * @author Marjan Mehranfar
 */
public class CardHolderRequest {
    final String pinType = "EPAY";
    final String pan;
    final String pin;
    final String cvv2;
    final String expDate;
    final String destinationPan;

    public CardHolderRequest(String pan, String pin, String cvv2, String expDate, String destinationPan) {
        this.pan = pan;
        this.pin = pin;
        this.cvv2 = cvv2;
        this.expDate = expDate;
        this.destinationPan = destinationPan;
    }
}
