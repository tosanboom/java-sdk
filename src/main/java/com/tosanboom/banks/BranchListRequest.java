package com.tosanboom.banks;

import com.tosanboom.Asserts;
import com.tosanboom.Bank;

import static com.tosanboom.QueryParams.newQuery;

/**
 * Encapsulates request parameters to list branches of a particular bank. The only
 * required parameter is the {@linkplain #bank} parameter. The {@linkplain #length} and
 * {@linkplain #offset} are optional but if given will be subject to validation.
 *
 * @author Ali Dehghani
 */
public class BranchListRequest {
    final Bank bank;
    Long length;
    Long offset;

    /**
     * Create a request to filter branches of the given {@code bank} with default
     * pagination parameters
     *
     * @param bank The bank to list its branches
     * @throws IllegalArgumentException If the {@code bank} was {@code null}
     */
    public BranchListRequest(Bank bank) {
        this(bank, null, null);
    }

    /**
     * Create a request to filter branches of the given {@code bank} with pagination parameters
     * determined by the {@code length} and {@code offset}
     *
     * <p>If {@code length} or {@code offset} were given, they will be validated
     *
     * @param bank The bank to list its branches
     * @param length The page size
     * @param offset The offset from the beginning
     * @throws IllegalArgumentException If one of the parameters were missing or invalid
     */
    public BranchListRequest(Bank bank, Long length, Long offset) {
        Asserts.notNull(bank, "bank can't be null");
        assertPaginationParameters(length, offset);

        this.bank = bank;
        this.length = length;
        this.offset = offset;
    }

    private void assertPaginationParameters(Long length, Long offset) {
        if (length != null && length <= 0)
            throw new IllegalArgumentException("length can't be less than or equal to zero");

        if (offset != null && offset < 0)
            throw new IllegalArgumentException("offset can't be negative value");
    }

    String paginationQueryParam() {
        return newQuery().with("length", length).with("offset", offset).toString();
    }
}