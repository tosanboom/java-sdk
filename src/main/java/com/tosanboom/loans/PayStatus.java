package com.tosanboom.loans;

/**
 * Represents state of a loan payment
 *
 * @author Ali Dehghani
 */
public enum PayStatus {
    PAID,
    NOT_PAID_BEFORE_MATURITY,
    NOT_PAID_AFTER_MATURITY,
    UNKNOWN
}