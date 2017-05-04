package ir.boommarket.cards;

/**
 * Encapsulates filtering options available to filter the list of cards
 *
 * <p>If you don't want to filter the returned card list, just create a {@linkplain CardListRequest} with
 * an empty filtering schema:
 * <pre>
 *     CardListRequest cardListRequest = CardListRequest.withoutFilter()
 * </pre>
 * Otherwise, you can chain multiple parameters using the provided fluent interface:
 * <pre>
 *     CardListRequest request = CardListRequest.newBuilder()
 *                                              .withPan(pan)
 *                                              .withDepositNumber(depositNumber)
 *                                              .withCardStatus(cardStatus)
 *                                              .withOffset(offset)
 *                                              .withLength(length)
 *                                              .build()
 * </pre>
 *
 * @author Marjan Mehranfar
 */
public class CardListRequest {
    final String pan;
    final String depositNumber;
    final CardStatus cardStatus;
    final Long offset;
    final Long length;

    private CardListRequest(String pan, String depositNumber, CardStatus cardStatus, Long offset, Long length) {

        panNotEmpty(pan);
        depositNotEmpty(depositNumber);
        offsetNotNegative(offset);
        lengthNotNegative(length);

        this.pan = pan;
        this.depositNumber = depositNumber;
        this.cardStatus = cardStatus;
        this.offset = offset;
        this.length = length;
    }

    /**
     * If you don't want to filter the list of possible cards by various options, use this factory
     * method to create a filter-less request.
     *
     * @return An instance of {@link CardListRequest} class
     */
    public static CardListRequest withoutFilter() {
        return new Builder().build();
    }

    /**
     * A nexus to the {@linkplain Builder} which allows you to define different options to filter
     * the possible list of cards
     *
     * @return An instance of {@link CardListRequest} class
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * A builder for {@link CardListRequest} class
     */
    public static class Builder {
        private String pan;
        private String depositNumber;
        private CardStatus cardStatus;
        private Long offset;
        private Long length;

        /**
         * If given, the returned list will only contains card(s) with the given card number
         *
         * @param pan The card or PAN number
         * @return The builder itself
         */
        public Builder withPan(String pan) {
            this.pan = pan;
            return this;
        }

        /**
         * If given, the returned card list will contains all cards associated with the given
         * {@code depositNumber}
         *
         * @param depositNumber The deposit number to filter cards
         * @return The builder itself
         */
        public Builder withDepositNumber(String depositNumber) {
            this.depositNumber = depositNumber;
            return this;
        }

        /**
         * If given, the returned card list will contains cards which are in the given {@code cardStatus}
         *
         * @param cardStatus State of the card
         * @return The builder itself
         */
        public Builder withCardStatus(CardStatus cardStatus) {
            this.cardStatus = cardStatus;
            return this;
        }

        /**
         * Set offset for pagination
         *
         * @param offset The non-negative offset value
         * @return The builder itself
         */
        public Builder withOffset(Long offset) {
            this.offset = offset;
            return this;
        }

        /**
         * Set the size of each page, defaults to 1. This value should be a positive number.
         *
         * @param length The length or size of each page
         * @return The builder itself
         */
        public Builder withLength(Long length) {
            this.length = length;
            return this;
        }

        /**
         * @throws IllegalArgumentException If one of the given parameters weren't valid
         */
        public CardListRequest build() {
            return new CardListRequest(pan, depositNumber, cardStatus, offset, length);
        }
    }

    /**
     * If {@code depositNumber} wasn't null, this method will check the given {@code depositNumber} is not
     * a blank string, Otherwise it throws an {@linkplain IllegalArgumentException} with an appropriate message.
     *
     * @param depositNumber The depositNumber
     * @throws IllegalArgumentException If the given {@code depositNumber} was a blank string
     */
    private void depositNotEmpty(String depositNumber) {
        if(depositNumber != null && depositNumber.trim().isEmpty())
            throw new IllegalArgumentException("DepositNumber can't be a blank string");
    }

    /**
     * If {@code pan} wasn't null, this method will check the given {@code pan} is not a blank string,
     * Otherwise it throws an {@linkplain IllegalArgumentException} with an appropriate message
     *
     * @param pan the pan of card
     * @throws IllegalArgumentException If the given pan was a blank string
     */
    private void panNotEmpty(String pan) {
        if(pan != null && pan.trim().isEmpty())
            throw new IllegalArgumentException("Pan can't be a blank string");
    }

    /**
     * If {@code offset} was not null, This method check it not be a negative number,
     * Otherwise it throws an {@linkplain IllegalArgumentException} with an appropriate message
     *
     * @param offset How many items to skip before the first element of the current page (offset)
     * @throws IllegalArgumentException If the given offset was a negative number
     */
    private void offsetNotNegative(Long offset) {
        if(offset != null && offset < 0)
            throw new IllegalArgumentException("Offset can't be a negative number");
    }

    /**
     * If {@code length} has not null value, This method check it not be a non-positive number,
     * Otherwise it throws an {@linkplain IllegalArgumentException} with an appropriate message
     *
     * @param length The page size or length
     * @throws IllegalArgumentException If the given length was less than or equal to zero
     */
    private void lengthNotNegative(Long length) {
        if(length != null && length <= 0)
            throw new IllegalArgumentException("Length can't be less than or equal to zero");
    }
}
