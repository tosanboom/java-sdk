package com.tosanboom;

/**
 * Encapsulates each bank information such as its <em>SWIFT Code</em>
 *
 * @author Ali Dehghani
 */
public enum Bank {
    SHAHR("CIYBIR"),
    SAMAN("SABCIR"),
    EGHTESAD_NOVIN("BEGNIR"),
    HEKMAT("HEKMIR"),
    ANSAR("ANSBIR"),
    DAY("DAYBIR"),
    SINA("SINAIR"),
    SARMAYE("SRMBIR"),
    IRAN_VENEZUELA("IVBBIR"),
    IRAN_ZAMIN("IRZAIR"),
    MEHR_IRAN("MEHRIR"),
    MEHR_EGHTESAD("MEDBIR");

    private final String swiftCode;

    Bank(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    /**
     * The unique identification code for financial institutions
     *
     * @see <a href="https://en.wikipedia.org/wiki/ISO_9362">SWIFT Code</a>
     *
     * @return The corresponding bank's swift code
     */
    public String swiftCode() {
        return swiftCode;
    }
}