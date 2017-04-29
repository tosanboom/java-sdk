package com.tosanboom.cards;

/**
 * Encapsulates all parameters for calling the card holder service. All parameters are
 * mandatory except for the {@linkplain #destinationPan} which is optional.
 *
 * <p>If the {@linkplain #destinationPan} wasn't given, then the card holder service will
 * return the information for a card identified by {@linkplain #pan} number. Otherwise, the
 * returned information would belong to a card identified by {@linkplain #destinationPan}.
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

    /**
     * Creates a valid instance of {@linkplain CardHolderRequest}
     *
     * @param pan The card number
     * @param pin The pin for a card identified by {@linkplain #pan}
     * @param cvv2 The cvv2 for a card identified by {@linkplain #pan}
     * @param expDate The expiration date for identified by {@linkplain #pan}
     * @param destinationPan If given, the card service will return personal information of this
     *                       card number
     */
    public CardHolderRequest(String pan, String pin, String cvv2, String expDate, String destinationPan) {
        this.pan = pan;
        this.pin = pin;
        this.cvv2 = cvv2;
        this.expDate = expDate;
        this.destinationPan = destinationPan;
    }
}