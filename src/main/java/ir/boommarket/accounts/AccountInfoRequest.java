package ir.boommarket.accounts;

import static ir.boommarket.QueryParams.newQuery;

/**
 * Encapsulates filtering options available for get account info service. Using this
 * request abstraction you can determine whether or not your addresses show up in the
 * response and if so, how many of them appear in each request.
 *
 * @author Ali Dehghani
 */
public class AccountInfoRequest {
    final boolean hasAddress;
    final Long length;
    final Long offset;

    private AccountInfoRequest(boolean hasAddress, Long length, Long offset) {
        validatePaginationParameters(length, offset);

        this.hasAddress = hasAddress;
        this.length = length;
        this.offset = offset;
    }

    String toQueryParam() {
        return newQuery()
                .with("has_address", hasAddress)
                .with("length", length)
                .with("offset", offset)
                .toString();
    }

    /**
     * Creates an instance of {@linkplain AccountInfoRequest} that request for basic account information
     * without address
     *
     * @return An instance of {@linkplain AccountInfoRequest}
     */
    public static AccountInfoRequest withoutAddress() {
        return newBuilder().build();
    }

    /**
     * A factory method that acts as a nexus to the {@linkplain Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * A fluent builder for {@linkplain AccountInfoRequest}. You can chain different building operations together and
     * whenever you feel this is the time, build an instance of {@linkplain AccountInfoRequest} using the
     * notorious {@linkplain #build()} method.
     *
     * <p>Please note all validations are deferred until you call the {@linkplain #build()} method.
     */
    public static class Builder {
        boolean hasAddress = false;
        Long length;
        Long offset;

        /**
         * Instruct the service to show account addresses
         *
         * @return The builder itself
         */
        public Builder showAddresses() {
            hasAddress = true;
            return this;
        }

        /**
         * Determines the page size for addresses. If given, it should be greater than zero
         *
         * @param length The page size.
         * @return The builder itself
         */
        public Builder withLength(Long length) {
            this.length = length;
            return this;
        }

        /**
         * Determines number of addresses to skip. By default, it starts from the beginning and
         * if given, its value should greater than or equal to zero.
         *
         * @param offset The offset from the beginning
         * @return The builder itself
         */
        public Builder withOffset(Long offset) {
            this.offset = offset;
            return this;
        }

        /**
         * Construct an instance of {@linkplain AccountInfoRequest} from the accumulated state
         *
         * @return An instance of {@linkplain AccountInfoRequest}
         * @throws IllegalArgumentException When pagination parameters were invalid
         */
        public AccountInfoRequest build() {
            return new AccountInfoRequest(hasAddress, length, offset);
        }
    }

    private void validatePaginationParameters(Long length, Long offset) {
        if (length != null && length <= 0)
            throw new IllegalArgumentException("Length can't be less than or equal to zero");

        if (offset != null && offset < 0)
            throw new IllegalArgumentException("Offset can't be less than zero");
    }
}