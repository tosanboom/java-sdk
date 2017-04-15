package com.tosanboom;

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

    public String swiftCode() {
        return swiftCode;
    }
}