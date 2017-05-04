package ir.boommarket.cards;

import java.math.BigDecimal;

/**
 * Encapsulate mandatory parameters that must be set to transfer some amount with card.
 * By choose type of destination, can transfer some amount to card or deposit.
 *
 * @author Mona Mohamadinia
 */
public class CardTransferRequest {
    final String pinType = "EPAY";
    final String pan;
    final String pin;
    final String cvv2;
    final String expDate;
    final String destination;
    final TransferDestinationType destinationType;
    final BigDecimal amount;

    /**
     * Constructing an instance of {@linkplain CardTransferRequest}
     *
     * @param pan The card number
     * @param pin The card pin, i.e. second password
     * @param cvv2 CVV2 for the card
     * @param expDate The expiration date of the card in {@code yymm} format in Hejri Shamsi, e.g. 9911.
     * @param destination The destination of transfer, it could be pan or deposit number.
     * @param destinationType Transfer destination type which represents the type of destination. If set
     *                       {@linkplain TransferDestinationType#DEPOSIT} then the {@code destination} value
     *                        would be the deposit number. Otherwise, it would be a card number.
     * @param amount The amount of transfer in Rails
     * @throws IllegalArgumentException When either of the given parameters were missing or invalid
     */
    public CardTransferRequest(String pan, String pin, String cvv2, String expDate, String destination,
                               TransferDestinationType destinationType, BigDecimal amount) {
        panNotEmpty(pan);
        pinNotEmpty(pin);
        cvv2NotEmpty(cvv2);
        expDateNotEmpty(expDate);
        destinationNotEmpty(destination);
        transferDestinationTypeNotNull(destinationType);
        amountNotNegativeOrZero(amount);

        this.pan = pan;
        this.pin = pin;
        this.cvv2 = cvv2;
        this.expDate = expDate;
        this.destination = destination;
        this.destinationType = destinationType;
        this.amount = amount;
    }

    private void amountNotNegativeOrZero(BigDecimal amount) {
        if(amount == null || amount.signum() <= 0)
            throw new IllegalArgumentException("Amount can't be null or negative");
    }

    private void transferDestinationTypeNotNull(TransferDestinationType destinationType) {
        if (destinationType == null)
            throw new IllegalArgumentException("Destination type can't be null");
    }

    private void destinationNotEmpty(String destination) {
        if (destination == null || destination.trim().isEmpty())
            throw new IllegalArgumentException("Destination can't be a blank string");
    }

    private void expDateNotEmpty(String expDate) {
        if (expDate == null || expDate.trim().isEmpty())
            throw new IllegalArgumentException("ExpDate can't be a blank string");
    }

    private void cvv2NotEmpty(String cvv2) {
        if (cvv2 == null || cvv2.trim().isEmpty())
            throw new IllegalArgumentException("cvv2 can't be a blank string");
    }

    private void pinNotEmpty(String pin) {
        if (pin == null || pin.trim().isEmpty())
            throw new IllegalArgumentException("Pin can't be a blank string");
    }

    private void panNotEmpty(String pan) {
        if (pan == null || pan.trim().isEmpty())
            throw new IllegalArgumentException("Pan can't be a blank string");
    }
}